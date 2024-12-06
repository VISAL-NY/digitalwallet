package com.bill24.digitalwalletsdk.data.apdapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.data.local.DefaultApperance;
import com.bill24.digitalwalletsdk.domain.conststant.Constant;
import com.bill24.digitalwalletsdk.domain.helper.CustomView;
import com.bill24.digitalwalletsdk.domain.helper.TextStyleHelper;
import com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType.AvailablePaymentMethodType;

import java.util.List;

public class MainPaymentMethodAdapter extends RecyclerView.Adapter<MainPaymentMethodAdapter.MainPaymentMethodViewHolder> {
    private OnAvailablePaymentTypeClickListener clickListener;
    private List<AvailablePaymentMethodType> itemList;
    private String language;

    public  MainPaymentMethodAdapter(List<AvailablePaymentMethodType> itemList,OnAvailablePaymentTypeClickListener clickListener,
                                     String language){
        this.itemList=itemList;
        this.clickListener=clickListener;
        this.language=language;
    }

    @NonNull
    @Override
    public MainPaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_payment_method_card,parent,false);
        return new MainPaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPaymentMethodViewHolder holder, int position) {
        AvailablePaymentMethodType item=itemList.get(position);


        CustomView.setRoundedBackgroundWithShadow(holder.container, DefaultApperance.getCardColor(),20,8);
        CustomView.setRoundedBackground(holder.BgIcon,DefaultApperance.getPrimaryColor(),20);


        TextStyleHelper.styleTextView(
                holder.title,language.equals(Constant.KHMER_CODE) && !item.getNameKh().isEmpty()?R.font.battambang_bold:R.font.roboto_bold,
                16,
                DefaultApperance.getLabelColor(),
                language.equals(Constant.KHMER_CODE) && !item.getNameKh().isEmpty()? item.getNameKh() :item.getName(),
                holder.itemView.getContext());


        if(item.getLogo().isEmpty()){
            if(item.getType().equals(Constant.WALLET)){
                Drawable drawable=holder.itemView.getContext().getDrawable(R.drawable.account_balance_wallet_24px);
                if(drawable!=null){
                    holder.logo.setImageDrawable(drawable);
                    holder.logo.setImageTintList(ColorStateList.valueOf(Color.parseColor(DefaultApperance.getButtonTextColor())));
                }
            }
            if(item.getType().equals(Constant.CARD)){
                Drawable drawable=holder.itemView.getContext().getDrawable(R.drawable.credit_card_24px);
                if(drawable!=null){
                    holder.logo.setImageDrawable(drawable);
                    holder.logo.setImageTintList(ColorStateList.valueOf(Color.parseColor(DefaultApperance.getButtonTextColor())));
                }
            }
            if(item.getType().equals(Constant.BANK)){
                Drawable drawable=holder.itemView.getContext().getDrawable(R.drawable.account_balance_24px);
                if(drawable!=null){
                    holder.logo.setImageDrawable(drawable);
                    holder.logo.setImageTintList(ColorStateList.valueOf(Color.parseColor(DefaultApperance.getButtonTextColor())));
                }
            }
        }

        // set animation when bind data on screen
        holder.itemView.setAlpha(0f); // Initially hidden
        holder.itemView.animate()
                .alpha(1f) // Fade to full visibility
                .setDuration(500) // Duration of the fade-in effect
                .start();

        //set call back event on item click
        holder.itemView.setOnClickListener(view -> clickListener.OnAvailableItemClick(item));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MainPaymentMethodViewHolder extends RecyclerView.ViewHolder{
       private AppCompatImageView logo;
       private AppCompatTextView title;
       private LinearLayoutCompat container;
       private FrameLayout BgIcon;

        public MainPaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);
           logo=itemView.findViewById(R.id.main_payment_method_icon);
           title=itemView.findViewById(R.id.main_payment_method_title);
           container=itemView.findViewById(R.id.main_payment_method_container);
           BgIcon=itemView.findViewById(R.id.main_payment_method_bg_icon);
        }
    }

    public  interface OnAvailablePaymentTypeClickListener{
        void OnAvailableItemClick(AvailablePaymentMethodType type);
    }

}
