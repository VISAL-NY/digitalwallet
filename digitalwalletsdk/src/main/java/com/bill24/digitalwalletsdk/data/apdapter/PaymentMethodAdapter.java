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
import androidx.recyclerview.widget.RecyclerView;

import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.data.local.DefaultApperance;
import com.bill24.digitalwalletsdk.domain.conststant.Constant;
import com.bill24.digitalwalletsdk.domain.conststant.translate.Translate;
import com.bill24.digitalwalletsdk.domain.helper.CustomView;
import com.bill24.digitalwalletsdk.domain.helper.SetOpacityColor;
import com.bill24.digitalwalletsdk.domain.helper.TextStyleHelper;
import com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod.PaymentMethod;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {

    private OnPaymentMethodClickListener clickListener;
    private List<PaymentMethod> itemList;
    private String language;

    public PaymentMethodAdapter(List<PaymentMethod> itemList,

            OnPaymentMethodClickListener clickListener,
                                String language){
        this.itemList=itemList;
        this.clickListener=clickListener;
        this.language=language;
    }
    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_card,parent,false);
        return new PaymentMethodViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        PaymentMethod item=itemList.get(position);


        CustomView.setRoundedBackgroundWithShadow(holder.itemCardContainer,DefaultApperance.getCardColor(),20,8);
        CustomView.setRoundedBackground(holder.BgIcon,DefaultApperance.getPrimaryColor(),20);
        TextStyleHelper.styleTextView(
                holder.title,language.equals(Constant.KHMER_CODE) && !item.getTitleKh().isEmpty()?R.font.battambang_bold:R.font.roboto_bold,
                16,
                DefaultApperance.getLabelColor(),
                language.equals(Constant.KHMER_CODE) && !item.getTitleKh().isEmpty()? item.getTitleKh() :item.getTitle(),
                holder.itemView.getContext());

        TextStyleHelper.styleTextView(
                holder.subTitle,language.equals(Constant.KHMER_CODE) && !item.getSubTitleKh().isEmpty()?R.font.battambang_regular:R.font.roboto_regular,
                14,
                DefaultApperance.getLabelColor(),
                language.equals(Constant.KHMER_CODE) && !item.getSubTitleKh().isEmpty()? item.getTitleKh() :item.getTitle(),
                holder.itemView.getContext());
        holder.subTitle.setTextColor(SetOpacityColor.opacityColor(DefaultApperance.getLabelColor(),0.8f));


        if(!item.isDefault()){
            holder.defaultIcon.setVisibility(View.INVISIBLE);
        }
        holder.defaultIcon.setColorFilter(Color.parseColor(DefaultApperance.getPrimaryColor()));




        if(!item.getLogo().isEmpty()){
            ViewGroup.LayoutParams params=holder.logo.getLayoutParams();
            params.width=ViewGroup.LayoutParams.MATCH_PARENT;
            params.height=ViewGroup.LayoutParams.MATCH_PARENT;
            holder.logo.setLayoutParams(params);
            Picasso.get()
                    .load(item.getLogo())
                    .placeholder(R.drawable.account_balance_wallet_24px)
                    .into(holder.logo);
        }else {
            holder.BgIcon.setPadding(30,30,30,30);
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

        //set overlay color
        if(item.getStatus().equals("inactive")){
            CustomView.setRoundedBackgroundWithShadow(holder.overlayContainer,DefaultApperance.getPrimaryColor(),20,8,0.4f);

            TextStyleHelper.styleTextView(
                    holder.overlayTitle,
                    language.equals(Constant.KHMER_CODE)?R.font.battambang_regular:R.font.roboto_regular,
                    14,
                    DefaultApperance.getLabelColor(),
                    language.equals(Constant.KHMER_CODE)?Translate.ACC_INACTIVATE_KH:Translate.ACC_INACTIVATE_EN,
                    holder.itemView.getContext()
                    );

            Drawable drawable=holder.overlayIcon.getDrawable();
            drawable.setTint(Color.parseColor(DefaultApperance.getLabelColor()));
        }else {
            holder.overlayContainer.setBackgroundColor(Color.TRANSPARENT);
            holder.overlayTitle.setTextColor(Color.TRANSPARENT);
            Drawable drawable=holder.overlayIcon.getDrawable();
            drawable.setTint(Color.TRANSPARENT);
        }


        // set animation when bind data on screen
        holder.itemView.setAlpha(0f); // Initially hidden
        holder.itemView.animate()
                .alpha(1f) // Fade to full visibility
                .setDuration(200) // Duration of the fade-in effect
                .start();


        //set call back event on item click
        holder.itemView.setOnClickListener(view -> clickListener.OnPaymentMethodClick(item));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    class PaymentMethodViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView logo,defaultIcon,overlayIcon;
        private AppCompatTextView title,subTitle,overlayTitle;
        private FrameLayout itemCardContainer,BgIcon,overlayContainer;
        public PaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);
            logo=itemView.findViewById(R.id.payment_method_icon);
            title=itemView.findViewById(R.id.payment_method_title);
            subTitle=itemView.findViewById(R.id.payment_method_sub_title);
            defaultIcon=itemView.findViewById(R.id.payment_method_default_icon);
            itemCardContainer=itemView.findViewById(R.id.payment_method_item_card);
            BgIcon=itemView.findViewById(R.id.payment_method_bg_icon);
            overlayContainer=itemView.findViewById(R.id.pm_container_overlay);
            overlayIcon=itemView.findViewById(R.id.pm_overlay_icon);
            overlayTitle=itemView.findViewById(R.id.pm_overlay_title);

        }
    }

    public  interface OnPaymentMethodClickListener{
        void OnPaymentMethodClick(PaymentMethod paymentMethod);
    }

}

