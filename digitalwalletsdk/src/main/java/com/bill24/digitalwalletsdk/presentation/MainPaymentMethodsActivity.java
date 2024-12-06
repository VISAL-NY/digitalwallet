package com.bill24.digitalwalletsdk.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.data.apdapter.MainPaymentMethodAdapter;
import com.bill24.digitalwalletsdk.data.api.ApiRequest;
import com.bill24.digitalwalletsdk.data.local.DefaultApperance;
import com.bill24.digitalwalletsdk.databinding.ActivityAddPaymentMethodsBinding;
import com.bill24.digitalwalletsdk.domain.conststant.Constant;
import com.bill24.digitalwalletsdk.domain.conststant.translate.Translate;
import com.bill24.digitalwalletsdk.domain.helper.SetOpacityColor;
import com.bill24.digitalwalletsdk.domain.helper.TextStyleHelper;
import com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType.AvailablePaymentMethodRequestModel;
import com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType.AvailablePaymentMethodType;
import com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType.AvailablePaymentMethodTypeResponseModel;
import com.bill24.digitalwalletsdk.domain.model.base.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPaymentMethodsActivity extends AppCompatActivity implements MainPaymentMethodAdapter.OnAvailablePaymentTypeClickListener{

    ActivityAddPaymentMethodsBinding apmBinding;
    private String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        apmBinding =ActivityAddPaymentMethodsBinding.inflate(getLayoutInflater());
        setContentView(apmBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent=getIntent();
        if(intent!=null){
            language=intent.getStringExtra(Constant.LANGUAGE_KEY);
        }

        onViewBound();
        applyFontAppearance(this);
        getAvailablePaymentType(this);

    }

    private void onViewBound(){
        setSupportActionBar(apmBinding.apmToolbar);
        apmBinding.apmNavigationBack.setOnClickListener(view ->getOnBackPressedDispatcher().onBackPressed());
    }

    private void bindRecyclerView(Context context, List<AvailablePaymentMethodType> items){
        apmBinding.apmRecyclerview.setLayoutManager(new LinearLayoutManager(context));

        MainPaymentMethodAdapter adapter=new MainPaymentMethodAdapter(items,this,language);
        apmBinding.apmRecyclerview.setAdapter(adapter);
    }

    private void applyFontAppearance(Context context){
        apmBinding.main.setBackgroundColor(Color.parseColor(DefaultApperance.getSecondaryColor()));
        apmBinding.apmToolbar.setBackgroundColor(Color.parseColor(DefaultApperance.getSecondaryColor()));

        TextStyleHelper.styleTextView(apmBinding.apmToolbarTitle,
                language.equals(Constant.ENGLISH_CODE)?  R.font.roboto_bold :R.font.battambang_bold,
                20,DefaultApperance.getLabelColor(),
                language.equals(Constant.ENGLISH_CODE)?  Translate.ADD_PAYMETHOD_TITLE_EN :Translate.ADD_PAYMETHOD_TITLE_KH ,context);

        apmBinding.apmNavigationBack.setColorFilter(Color.parseColor(DefaultApperance.getLabelColor()));
        apmBinding.apmBottomLine.setBackgroundColor(SetOpacityColor.opacityColor(DefaultApperance.getLabelColor(),0.3f));


    }

    private void getAvailablePaymentType(Context context){
        ApiRequest apiRequest=new ApiRequest("dkkd");
        AvailablePaymentMethodRequestModel model=new AvailablePaymentMethodRequestModel("user001");
        Call<BaseResponse<AvailablePaymentMethodTypeResponseModel>> call=apiRequest.getAvailablePaymentMethodType(model);
        call.enqueue(new Callback<BaseResponse<AvailablePaymentMethodTypeResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<AvailablePaymentMethodTypeResponseModel>> call, Response<BaseResponse<AvailablePaymentMethodTypeResponseModel>> response) {
                if(response.isSuccessful()){
                   List<AvailablePaymentMethodType> paymentMethodTypeList=response.body().getData().getAvailablePaymentMethodType();
                    bindRecyclerView(context,paymentMethodTypeList);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AvailablePaymentMethodTypeResponseModel>> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void OnAvailableItemClick(AvailablePaymentMethodType type) {
        Toast.makeText(this, ""+type.getName(), Toast.LENGTH_SHORT).show();

    }
}