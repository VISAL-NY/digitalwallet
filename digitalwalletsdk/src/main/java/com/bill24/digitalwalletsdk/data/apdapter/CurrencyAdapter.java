package com.bill24.digitalwalletsdk.data.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.domain.model.Currency;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    private List<Currency> currencyList;
    private LayoutInflater inflater;

    public  CurrencyAdapter(@NonNull Context context,@NonNull List<Currency> objects){
        super(context,0,objects);
        this.currencyList=objects;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.drop_down_currency_items,parent,false);
        }

        Currency currency=currencyList.get(position);
        AppCompatImageView currencyIcon=convertView.findViewById(R.id.currency_icon);
        AppCompatTextView currencyCode=convertView.findViewById(R.id.currency_code);

        currencyCode.setText(currency.getCurrencyCode());

        return convertView;
    }
}
