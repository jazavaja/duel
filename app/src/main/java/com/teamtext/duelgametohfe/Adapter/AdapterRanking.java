package com.teamtext.duelgametohfe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamtext.duelgametohfe.Model.rank.ResultRank;
import com.teamtext.duelgametohfe.R;


public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.ViewHolder> {
    private ResultRank[] resultRank;
    Context context;
    public AdapterRanking(ResultRank[] resultComments, Context context)
    {
        this.context=context;
        this.resultRank =resultComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.row_ranking, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.name.setText(resultRank[position].getName());
        holder.point.setText(resultRank[position].getPoint());
    }


    @Override
    public int getItemCount()
    {
        return resultRank.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView point;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name =itemView.findViewById(R.id.showUserName);
            point=itemView.findViewById(R.id.showUserRank);
        }
    }
}
