package com.bill24.digitalwalletsdk.presentation;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.data.apdapter.CurrencyAdapter;
import com.bill24.digitalwalletsdk.databinding.ActivityCreateWalletBinding;
import com.bill24.digitalwalletsdk.domain.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CreateWalletActivity extends AppCompatActivity {

    private ActivityCreateWalletBinding cwlBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        cwlBinding=ActivityCreateWalletBinding.inflate(getLayoutInflater());
        setContentView(cwlBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        onBoundToolbar();
        bindDataCurrency(this);
    }

    private void onBoundToolbar(){
        setSupportActionBar(cwlBinding.cwlToolbar);
        cwlBinding.cwlToolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
    }

    private void bindDataCurrency(Context context){
        List<Currency> currencies=new ArrayList<>();
        currencies.add(new Currency("", "USD"));
        currencies.add(new Currency("", "EUR"));
        currencies.add(new Currency("", "JPY"));

        CurrencyAdapter adapter=new CurrencyAdapter(context,currencies);
        cwlBinding.autoCompleteCurrency.setAdapter(adapter);

        cwlBinding.autoCompleteCurrency.setOnItemClickListener((adapterView, view, i, l) -> {
            Currency currency = (Currency) adapterView.getItemAtPosition(i);

            cwlBinding.autoCompleteCurrency.setText(currency.getCurrencyCode(),true);
            cwlBinding.autoCompleteCurrency.showDropDown();
        });

    }
}