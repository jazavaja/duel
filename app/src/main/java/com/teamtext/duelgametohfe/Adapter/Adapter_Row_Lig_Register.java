package com.teamtext.duelgametohfe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.ModelKoli;
import com.teamtext.duelgametohfe.Model.game.Game;
import com.teamtext.duelgametohfe.Model.league.ResultLeague;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;
import com.teamtext.duelgametohfe.game.PlayGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;


public class Adapter_Row_Lig_Register extends RecyclerView.Adapter<Adapter_Row_Lig_Register.ViewHolder> {
    Context context;
    ResultLeague[] resultLeague;
    General general;
    ArrayList<Game> games;
//    General general;


    public Adapter_Row_Lig_Register(Context context, ResultLeague[] resultLeagues, ArrayList<Game> games) {
        this.context = context;
        this.resultLeague=resultLeagues;
        this.games=games;
//        this.general=general;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_select_league,parent,false);
        general=new General(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        Set<String> s=general.getAllLeagueRegister();
        holder.name.setText(resultLeague[position].getName());
        holder.coin.setText(resultLeague[position].getCostCoin());
        holder.ticket.setText(resultLeague[position].getCostTicket());
        Picasso.get().load(Req.rootImg+resultLeague[position].getPicture()).into(holder.picture);
        holder.payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                registerGame(resultLeague[position].getId());
            }
        });


    }


    @Override
    public int getItemCount() {
        return resultLeague.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView coin;
        TextView ticket;
        TextView name;
        Button payment;
        CircleImageView picture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coin=itemView.findViewById(R.id.coin);
            ticket=itemView.findViewById(R.id.ticket);
            name=itemView.findViewById(R.id.name);
            picture=itemView.findViewById(R.id.picture);
            payment=itemView.findViewById(R.id.payment);
        }
    }

    public void registerGame(int league){
        new Req(context, "registerLeague", true) {
            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson gson=new Gson();
                ModelKoli modelKoli=gson.fromJson(response,ModelKoli.class);
                if (modelKoli.getStatus()==200)
                {
                    goToGame(league);
                }else
                    Toast.makeText(context, modelKoli.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void ResponseError(VolleyError error) {

            }

            @Override
            public Map<String, String> Parameters() {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("token",general.getToken());
                hashMap.put("type","register");
                hashMap.put("league_id", String.valueOf(league));
                return hashMap;
            }
        };
    }
    public void goToGame(int league){

        Intent intent=new Intent(context, PlayGame.class);
        intent.putExtra("ID",league);
        context.startActivity(intent);
    }
}
