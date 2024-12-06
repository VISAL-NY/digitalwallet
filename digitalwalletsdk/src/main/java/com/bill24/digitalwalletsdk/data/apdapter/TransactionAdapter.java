package com.bill24.digitalwalletsdk.data.apdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{

    private OnTransactionItemClickListener clickListener;
    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions,OnTransactionItemClickListener clickListener){
        this.transactions=transactions;
        this.clickListener=clickListener;
    }


    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_items,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction=transactions.get(position);
        holder.title.setText(transaction.getTranDate());
        holder.subTitle.setText(transaction.getTranType() + position);
        holder.amount.setText(" "+transaction.getAmount());

        // set animation when bind data on screen
        holder.itemView.setAlpha(0f); // Initially hidden
        holder.itemView.animate()
                .alpha(1f) // Fade to full visibility
                .setDuration(500) // Duration of the fade-in effect
                .start();

        holder.itemView.setOnClickListener(view -> clickListener.onItemClick(transaction));

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
       private FrameLayout bgIcon;
       private AppCompatImageView icon;
       private AppCompatTextView title,subTitle,amount;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            bgIcon=itemView.findViewById(R.id.tran_bg_icon);
            icon=itemView.findViewById(R.id.tran_icon_type);
            title=itemView.findViewById(R.id.tran_tran_date);
            subTitle=itemView.findViewById(R.id.tran_tran_type);
            amount=itemView.findViewById(R.id.tran_tran_amount);
        }
    }

    public  interface OnTransactionItemClickListener{
        void onItemClick(Transaction transaction);
    }
}
