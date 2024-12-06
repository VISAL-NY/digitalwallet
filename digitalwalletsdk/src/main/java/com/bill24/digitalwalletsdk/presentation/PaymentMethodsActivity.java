package com.bill24.digitalwalletsdk.presentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.data.apdapter.PaymentMethodAdapter;
import com.bill24.digitalwalletsdk.data.api.ApiRequest;
import com.bill24.digitalwalletsdk.data.local.DefaultApperance;
import com.bill24.digitalwalletsdk.databinding.ActivityPaymentMethodsBinding;
import com.bill24.digitalwalletsdk.domain.conststant.Constant;
import com.bill24.digitalwalletsdk.domain.conststant.translate.Translate;
import com.bill24.digitalwalletsdk.domain.helper.SetOpacityColor;
import com.bill24.digitalwalletsdk.domain.helper.TextStyleHelper;
import com.bill24.digitalwalletsdk.domain.model.base.BaseResponse;
import com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod.InstantPaymentMethodRequestModel;
import com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod.InstantPaymentMethodResponseModel;
import com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod.PaymentMethod;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodsActivity extends AppCompatActivity implements PaymentMethodAdapter.OnPaymentMethodClickListener {

    private ActivityPaymentMethodsBinding pmsBinding;
    private String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        pmsBinding =ActivityPaymentMethodsBinding.inflate(getLayoutInflater());
        setContentView(pmsBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent=getIntent();
        if(intent !=null){
            language=intent.getStringExtra(Constant.LANGUAGE_KEY);
        }

        onViewBound();
        onNavigationBack();

        applyTextFontAndAppearance(this);
        addingPaymentMethod(this);
        getInstantPaymentMethod(this);

    }



    private void onViewBound(){
        setSupportActionBar(pmsBinding.pmToolbar);

    }
    private void onNavigationBack(){
        pmsBinding.pmNavigationBack.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
    }
    private void addingPaymentMethod(Context context){

        pmsBinding.pmFab.setOnClickListener(view -> {
            //Intent intent=new Intent(context, MainPaymentMethodsActivity.class);
            Intent intent=new Intent(context, MainPaymentMethodsActivity.class);
            intent.putExtra(Constant.LANGUAGE_KEY,language);
            context.startActivity(intent);
        });
    }

    private void applyTextFontAndAppearance(Context context){

        pmsBinding.main.setBackgroundColor(Color.parseColor(DefaultApperance.getSecondaryColor()));
        pmsBinding.pmToolbar.setBackgroundColor(Color.parseColor(DefaultApperance.getSecondaryColor()));
        pmsBinding.pmFab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(DefaultApperance.getPrimaryColor())));
        pmsBinding.pmFab.setImageTintList(ColorStateList.valueOf(Color.parseColor(DefaultApperance.getButtonTextColor())));
        pmsBinding.pmNavigationBack.setColorFilter(Color.parseColor(DefaultApperance.getLabelColor()));
        pmsBinding.pmBottomLine.setBackgroundColor(SetOpacityColor.opacityColor(DefaultApperance.getLabelColor(),0.3f));
        TextStyleHelper.styleTextView(pmsBinding.pmToolbarTitle,
                language.equals(Constant.ENGLISH_CODE)?  R.font.roboto_bold :R.font.battambang_bold,
                    20,DefaultApperance.getLabelColor(),
                    language.equals(Constant.ENGLISH_CODE)?  Translate.PAYMETHOD_TITLE_EN :Translate.PAYMETHOD_TITLE_KH ,context);


    }


    private void hideIndicator(){
        pmsBinding.paymentMethodIndicator.setVisibility(View.GONE);
    }


    private void getInstantPaymentMethod(Context context){
        ApiRequest apiRequest=new ApiRequest("dkkd");
        InstantPaymentMethodRequestModel model=new InstantPaymentMethodRequestModel("user001","active");
        Call<BaseResponse<InstantPaymentMethodResponseModel>> call=apiRequest.getInstantPaymentMethod(model);

        call.enqueue(new Callback<BaseResponse<InstantPaymentMethodResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<InstantPaymentMethodResponseModel>> call, Response<BaseResponse<InstantPaymentMethodResponseModel>> response) {

                hideIndicator();
                if(response.isSuccessful()){
                   List<PaymentMethod> paymentMethods=response.body().getData().getPaymentMethod();
                   bindrecylerview(context,paymentMethods);

                }else {
                    Log.d("DDD1", "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<InstantPaymentMethodResponseModel>> call, Throwable throwable) {
                Log.d("EEE", "onResponse: "+throwable);
            }
        });
    }

    private void bindrecylerview(Context context,List<PaymentMethod> items){

        pmsBinding.pmRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        PaymentMethodAdapter adapter=new PaymentMethodAdapter(items,this,language);
        pmsBinding.pmRecyclerView.setAdapter(adapter);

        pmsBinding.pmRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void OnPaymentMethodClick(PaymentMethod paymentMethod) {
//        Toast.makeText(this, ""+paymentMethod.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AccountDetailActivity.class);
        intent.putExtra(Constant.LANGUAGE_KEY,language);
        intent.putExtra(Constant.SUB_TITLE_KEY,language.equals(Constant.ENGLISH_CODE)?paymentMethod.getSubTitle():paymentMethod.getSubTitleKh());
        intent.putExtra(Constant.PAYMENT_METHODE_ID,paymentMethod.getId());
        startActivity(intent);
    }


}