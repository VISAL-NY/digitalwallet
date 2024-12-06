package com.bill24.digitalwalletsdk;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bill24.digitalwalletsdk.databinding.ActivityMainBinding;
import com.bill24.digitalwalletsdk.manager.InstantPaymentSdk;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonAction(this);

    }

    private  void buttonAction(Context context){
        activityMainBinding.button.setOnClickListener(view -> {
           // Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
            InstantPaymentSdk.initSdk(context,"234","2344","km",true,false);
        });
    }


}