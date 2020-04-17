package com.teamtext.duelgametohfe.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.Adapter.AdapterShop;
import com.teamtext.duelgametohfe.CafeBazzar;
import com.teamtext.duelgametohfe.CafeBazzarInterface;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.MainActivity;
import com.teamtext.duelgametohfe.Model.shop.ModelShop;
import com.teamtext.duelgametohfe.Model.shop.ResultShop;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;
import com.teamtext.duelgametohfe.util.IabHelper;
import com.teamtext.duelgametohfe.util.IabResult;
import com.teamtext.duelgametohfe.util.Purchase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ir.tapsell.plus.AdRequestCallback;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.sdk.Tapsell;
import ir.tapsell.sdk.TapsellAdRequestListener;
import ir.tapsell.sdk.TapsellAdRequestOptions;
import ir.tapsell.sdk.TapsellAdShowListener;
import ir.tapsell.sdk.TapsellShowOptions;

import static ir.tapsell.sdk.TapsellAdActivity.ZONE_ID;


public class Fragment_Shop extends Fragment {
    private TabLayout tabLay;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private General general;
    private Context context;
    MainActivity mainActivity;
    private String ZONEID="5e72a713e7179500016193e9";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_page, container, false);
        refreshLayout = view.findViewById(R.id.refresh);
        general = new General(context);
        mainActivity=new MainActivity();
        tabLay = view.findViewById(R.id.tabLay);
        recyclerView = view.findViewById(R.id.rec);

        getShop(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getShop(true);
            }
        });
        getTabLayout();
        return view;
    }

    public void getTabLayout() {
        tabLay.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    requestAd();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                if (tab.getPosition()==0){
                    requestAd();
                }
            }
        });
    }

    public void getShop(boolean showDialog) {
        new Req(context, "getListShop", showDialog) {

            @Override
            public void NotFind() {
                refreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.GONE);

            }

            @Override
            public void ResponseSuccess(String response) {
                recyclerView.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(false);
                Gson gson = new Gson();
                ModelShop modelShop = gson.fromJson(response, ModelShop.class);
                if (modelShop.getStatus() == 200)
                {
                    ResultShop[] shop = gson.fromJson(modelShop.getResult(), ResultShop[].class);
                    assert getArguments() != null;
//                    if (getArguments().getSerializable("cafe")!=null){
//                        Log.e("ASASAS","ASASAS");
//                    }
                    CafeBazzar cafeBazzar= (CafeBazzar) getArguments().getParcelable("cafe");
                    AdapterShop adapterShop = new AdapterShop(shop, context,cafeBazzar);
                    recyclerView.setAdapter(adapterShop);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
            }

            @Override
            public void ResponseError(VolleyError error) {
                recyclerView.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> map = new HashMap<>();
                map.put("token", general.getToken());
                map.put("type", "get");
                return map;
            }
        };
    }

    private void requestAd() {
        Tapsell.requestAd(context,
                ZONEID,
                new TapsellAdRequestOptions(),
                new TapsellAdRequestListener() {
                    @Override
                    public void onAdAvailable(String adId)
                    {
                        showAd(context,adId,ZONEID);
                    }

                    @Override
                    public void onError(String message)
                    {
                        Toast.makeText(context, "تبلیغی فعلا وجود ندارد یا اتصال به اینترنت را چک نمایید سپس امتحان کنید", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void showAd(Context context,String AD_ID,String ZONe){
        Tapsell.showAd(context,
                ZONe,
                AD_ID,
                new TapsellShowOptions(),
                new TapsellAdShowListener() {
                    @Override
                    public void onOpened() {
                    }

                    @Override
                    public void onClosed() {
                    }

                    @Override
                    public void onError(String message) {
                    }

                    @Override
                    public void onRewarded(boolean completed) {
                        if (completed)
                            rewardForUserCoin();
                    }
                });
    }

    private void rewardForUserCoin() {
        new Req(context, "setReward", true) {
            @Override
            public void NotFind()
            {

            }
            @Override
            public void ResponseSuccess(String response) {
                Toast.makeText(context,"سکه به شما افزوده شد",Toast.LENGTH_LONG).show();
            }

            @Override
            public void ResponseError(VolleyError error) {
                rewardForUserCoin();
            }

            @Override
            public Map<String, String> Parameters()
            {
                Map<String,String> stringMap=new HashMap<>();
                stringMap.put("token",general.getToken());
                stringMap.put("coin","free");
                stringMap.put("ticket","nope");
                stringMap.put("type","set");
                return stringMap;
            }
        };
    }

}
