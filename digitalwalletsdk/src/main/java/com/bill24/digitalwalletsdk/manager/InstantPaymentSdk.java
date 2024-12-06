package com.bill24.digitalwalletsdk.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import android.widget.Toast;
import com.bill24.digitalwalletsdk.data.api.ApiRequest;
import com.bill24.digitalwalletsdk.data.local.DefaultApperance;
import com.bill24.digitalwalletsdk.domain.conststant.Constant;
import com.bill24.digitalwalletsdk.domain.model.InstantSdkConfig.InstantConfigResponseModel;
import com.bill24.digitalwalletsdk.domain.model.base.BaseResponse;
import com.bill24.digitalwalletsdk.presentation.PaymentMethodsActivity;
import com.google.android.material.progressindicator.CircularProgressIndicator;


import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstantPaymentSdk {

    private static String lanuage;
    public static void initSdk(Context context,String userSyncCode,String refererKey,String language,boolean isDarkMode,boolean isProduction){
        InstantPaymentSdk.lanuage=language;

        setLocale(context,language);
        getAppearance(context);

    }


    private  static  boolean isShowLoading=false;
    private static FrameLayout frameLayout;
    private static  void  showLoading(Context context,boolean isShow){
        if(context instanceof Activity){
            View rootView=((Activity)context).getWindow().getDecorView().getRootView();
            if(rootView!=null){
                if(isShow && !isShowLoading){
                    frameLayout=new FrameLayout(context);
                    frameLayout.setLayoutParams(new ViewGroup.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                    ));
                    int alpha1 = (int) (0.40 * 255);
                    int blackColorWith65PercentAlpha1 = Color.argb(alpha1, 0, 0, 0);
                    frameLayout.setBackgroundColor(blackColorWith65PercentAlpha1);
                    frameLayout.setClickable(true);


                    CircularProgressIndicator progressIndicator=new CircularProgressIndicator(context);
                    progressIndicator.setTrackThickness(11);
                    FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.WRAP_CONTENT,
                            FrameLayout.LayoutParams.WRAP_CONTENT
                    );

                    params.gravity= Gravity.CENTER;
                    progressIndicator.setIndeterminate(true);
                    progressIndicator.setIndicatorColor(Color.parseColor("#2196F3"));
                    frameLayout.addView(progressIndicator,params);


                    ((ViewGroup)rootView).addView(frameLayout);
                    progressIndicator.show();
                    isShowLoading=true;
                }else if(!isShow && isShowLoading) {
                    ((ViewGroup)rootView).removeView(frameLayout);
                    isShowLoading=false;
                }
            }
        }
    }


    private static void getAppearance(Context context){
        // Show progress bar
        showLoading(context,true);
        ApiRequest apiRequest=new ApiRequest("dkkklld");
        Call<BaseResponse<InstantConfigResponseModel>> call =apiRequest.getInstantConfig();

        call.enqueue(new Callback<BaseResponse<InstantConfigResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<InstantConfigResponseModel>> call, Response<BaseResponse<InstantConfigResponseModel>> response) {
                // Hide progress bar
                showLoading(context,false);

                if(response.isSuccessful()){
                    DefaultApperance.setAppearance(response.body().getData().getAppearance(), false);
                    Intent intent=new Intent(context, PaymentMethodsActivity.class);
                    intent.putExtra(Constant.LANGUAGE_KEY,lanuage);
                    context.startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<InstantConfigResponseModel>> call, Throwable throwable) {
                Toast.makeText(context, "Check you internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void setLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        // Create a configuration object to update the resources
        Configuration config = new Configuration();
        config.locale = locale;
        // For API level 17 and above, use setLocale in Configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        } else {
            // For older versions, use this approach
            Resources resources = context.getResources();
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }
    }


}
