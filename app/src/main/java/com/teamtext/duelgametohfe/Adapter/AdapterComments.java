package com.teamtext.duelgametohfe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamtext.duelgametohfe.Model.comment.ResultComments;
import com.teamtext.duelgametohfe.R;


public class AdapterComments extends RecyclerView.Adapter<AdapterComments.ViewHolder> {
    private ResultComments[] comments;
    Context context;
    public AdapterComments(ResultComments[] resultComments,Context context)
    {
        this.context=context;
        this.comments=resultComments;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.row_comment, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.title.setText(comments[position].getTitle());
        holder.content.setText(comments[position].getContent());
        holder.time.setText(comments[position].getCreatedAt());
        holder.reply.setText(comments[position].getReply());
    }

    @Override
    public int getItemCount()
    {
        return comments.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;
        TextView reply;
        ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById(R.id.content);
            time=itemView.findViewById(R.id.time);
            reply=itemView.findViewById(R.id.reply);
        }
    }
}
