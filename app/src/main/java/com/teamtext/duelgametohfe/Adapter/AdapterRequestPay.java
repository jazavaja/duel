package com.teamtext.duelgametohfe.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamtext.duelgametohfe.Model.request.ResultReq;
import com.teamtext.duelgametohfe.R;

public class AdapterRequestPay extends RecyclerView.Adapter<AdapterRequestPay.ViewHolder> {
  ResultReq[] resultReq;
  Context context;

  public AdapterRequestPay(Context context, ResultReq[] resultReq) {
    this.resultReq = resultReq;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View listItem = layoutInflater.inflate(R.layout.transaction_row, parent, false);
    return new ViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

    if (resultReq[position].getStatus().equals("0"))
      holder.status.setImageResource(R.drawable.timer_wait);
    if (resultReq[position].getStatus().equals("1"))
      holder.status.setImageResource(R.drawable.ic_success);
    if (resultReq[position].getStatus().equals("-1"))
      holder.status.setImageResource(R.drawable.ic_cancel);
    holder.requestId.setText(String.valueOf(resultReq[position].getRequestId()));
    holder.amount.setText(String.valueOf(resultReq[position].getAmount()));
    holder.transaction.setText(String.valueOf(resultReq[position].getTransaction()));
    holder.description.setText(resultReq[position].getDescription());
    holder.requestId.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view)
      {
        ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("1", holder.requestId.getText().toString());

      }
    });
  }

  @Override
  public int getItemCount() {
    return resultReq.length;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    ImageView status;
    TextView requestId;
    TextView amount;
    TextView transaction;
    TextView description;

    public ViewHolder(View itemView) {
      super(itemView);
      this.status = itemView.findViewById(R.id.my_status);
      this.requestId = itemView.findViewById(R.id.requestId);
      this.amount = itemView.findViewById(R.id.amount);
      this.transaction = itemView.findViewById(R.id.transaction);
      this.description = itemView.findViewById(R.id.description);
    }
  }
}
