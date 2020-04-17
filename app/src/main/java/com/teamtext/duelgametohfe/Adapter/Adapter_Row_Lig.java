package com.teamtext.duelgametohfe.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.league.ResultLeague;
import com.teamtext.duelgametohfe.Model.rank.Ranking;
import com.teamtext.duelgametohfe.Model.rank.ResultRank;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;

import java.util.HashMap;
import java.util.Map;

public class Adapter_Row_Lig extends RecyclerView.Adapter<Adapter_Row_Lig.ViewHolder> {
    Context context;
    ResultLeague[] resultLeague;
    View empty;
    RecyclerView recycle;
    General general;


    public Adapter_Row_Lig(Context context, ResultLeague[] resultLeagues, View empty, RecyclerView recycle, General general) {
        this.context = context;
        this.resultLeague=resultLeagues;
        this.empty=empty;
        this.general=general;
        this.recycle=recycle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_lig_all,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.textView.setText(resultLeague[position].getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getRanking(true,resultLeague[position].getId());
                Log.e("LeagueId", String.valueOf(resultLeague[position].getId()));
            }
        });
    }

    public void getRanking(boolean show, final int leagueId) {
        new Req(context, "getRank", show) {
            @Override
            public void NotFind()
            {
                empty.setVisibility(View.VISIBLE);
                recycle.setVisibility(View.GONE);
            }

            @Override
            public void ResponseSuccess(String response) {
                recycle.setVisibility(View.VISIBLE);
                empty.setVisibility(View.GONE);
                Gson gson = new Gson();
                Ranking ranking = gson.fromJson(response, Ranking.class);
                if (ranking.getStatus() == 200) {
                    ResultRank[] resultRank = gson.fromJson(ranking.getResult(), ResultRank[].class);
                    AdapterRanking adapterRanking = new AdapterRanking(resultRank, context);
                    recycle.setAdapter(adapterRanking);
                    adapterRanking.notifyDataSetChanged();
                    recycle.setLayoutManager(new LinearLayoutManager(context));

                }
            }

            @Override
            public void ResponseError(VolleyError error) {

            }

            @Override
            public Map<String, String> Parameters() {

                Map<String, String> map = new HashMap<>();
                map.put("token", general.getToken());
                map.put("type", "get");
                map.put("league_id", String.valueOf(leagueId));
                return map;
            }
        };
    }

    @Override
    public int getItemCount() {
        return resultLeague.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.ligName);

        }
    }
}
