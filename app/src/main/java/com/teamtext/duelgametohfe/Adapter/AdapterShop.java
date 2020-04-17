package com.teamtext.duelgametohfe.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.teamtext.duelgametohfe.CafeBazzar;
import com.teamtext.duelgametohfe.CafeBazzarInterface;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.MainActivity;
import com.teamtext.duelgametohfe.Model.ModelKoli;
import com.teamtext.duelgametohfe.Model.shop.ResultShop;
import com.teamtext.duelgametohfe.Payment;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;
import com.teamtext.duelgametohfe.util.IabHelper;
import com.teamtext.duelgametohfe.util.IabResult;
import com.teamtext.duelgametohfe.util.Purchase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterShop extends RecyclerView.Adapter<AdapterShop.ViewHolder> implements IabHelper.OnIabPurchaseFinishedListener{
    private ResultShop[] shops;
    private General general;
    private Context context;
    private CafeBazzar cafeBazzar;
    private int idForPurchase;

    public AdapterShop(ResultShop[] shop, Context context, CafeBazzar cafeBazzar) {
        this.context = context;
        this.shops = shop;
        this.cafeBazzar=cafeBazzar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.shop_row, parent, false);
        return new ViewHolder(listItem);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        general = new General(context);
        holder.name.setText(shops[position].getName());
        holder.description.setText(shops[position].getDescription());
        holder.coin.setText(String.valueOf(shops[position].getCoin()));
        holder.ticket.setText(String.valueOf(shops[position].getTicket()));
        final TextView price = holder.price;
        TextView newPrice = holder.newPrice;
        price.setText(String.valueOf(shops[position].getPrice()));
        Picasso.get().load(Req.rootImg + shops[position].getPicture()).into(holder.pic);

        if (Integer.parseInt(shops[position].getPrice()) > Integer.parseInt(shops[position].getNewPrice()))
        {
            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.linearLayoutNewPrice.setVisibility(View.VISIBLE);
            newPrice.setText(shops[position].getNewPrice());
        }
        Random random=new Random();

        holder.payment.setOnClickListener(view ->
        {
            idForPurchase=shops[position].getId();
            cafeBazzar.launchWithErrorLaunch((Activity) context,shops[position].getName(), 333,this,"developer-payload");
        });

    }



    private void sendRequestSuccessCafeBazzar(String id){
        new Req(context,"setRewardShop",true){

            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson s=new Gson();
                ModelKoli modelKoli=s.fromJson(response,ModelKoli.class);
                String sasan= String.valueOf(modelKoli.getMessage());
                Toast.makeText(context, sasan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void ResponseError(VolleyError error) {
                sendRequestSuccessCafeBazzar(id);
            }

            @Override
            public Map<String, String> Parameters() {
                Map<String,String> stringMap=new HashMap<>();
                stringMap.put("token",general.getToken());
                stringMap.put("type","set");
                stringMap.put("id",id);
                return stringMap;
            }
        };
    }


    public void payWithZarinPal(ResultShop[] shops,int position){
        Intent intent = new Intent();
        intent.setClass(context, Payment.class);
        String priceForPay = shops[position].getPrice();
        if (Integer.parseInt(shops[position].getNewPrice()) < Integer.parseInt(shops[position].getPrice()))
        {
            priceForPay = shops[position].getNewPrice();
        }
        intent.putExtra("pay", Req.getPaymentUrl(priceForPay, general.getToken(), String.valueOf(shops[position].getId())));
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return shops.length;
    }

    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase info) {
        if (result.isSuccess()) {
            Log.e("onIabPurchaseFinished","Success");
            cafeBazzar.consumeAsync(info, (purchase, result1) -> {
                if (result1.isSuccess())
                    sendRequestSuccessCafeBazzar(String.valueOf(idForPurchase));
//                    Log.e("onConsumeFinished","Success: "+ result1.getMessage());
                else if (result1.isFailure()){
                    Log.e("onConsumeFinished","Failure: "+ result1.getMessage());
                }
            });
        }
        else if (result.isFailure())
            Log.e("onIabPurchaseFinished","Failure");
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView coin;
        TextView ticket;
        TextView name;
        CircleImageView pic;
        TextView description;
        TextView price;
        TextView newPrice;
        LinearLayout linearLayoutPrice;
        LinearLayout linearLayoutNewPrice;
        Button payment;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            coin = itemView.findViewById(R.id.coin);
            ticket = itemView.findViewById(R.id.ticket);
            name = itemView.findViewById(R.id.name);
            pic = itemView.findViewById(R.id.picture);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            newPrice = itemView.findViewById(R.id.new_price);
            linearLayoutPrice = itemView.findViewById(R.id.linear_price);
            linearLayoutNewPrice = itemView.findViewById(R.id.linear_new_price);
            payment = itemView.findViewById(R.id.payment);
        }
    }
}
