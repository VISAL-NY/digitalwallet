package com.bill24.digitalwalletsdk.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bill24.digitalwalletsdk.R;
import com.bill24.digitalwalletsdk.data.apdapter.TransactionAdapter;
import com.bill24.digitalwalletsdk.data.api.ApiRequest;
import com.bill24.digitalwalletsdk.data.local.DefaultApperance;
import com.bill24.digitalwalletsdk.databinding.ActivityAccountDetailBinding;
import com.bill24.digitalwalletsdk.domain.conststant.Constant;
import com.bill24.digitalwalletsdk.domain.conststant.translate.Translate;
import com.bill24.digitalwalletsdk.domain.helper.CustomView;
import com.bill24.digitalwalletsdk.domain.helper.SetOpacityColor;
import com.bill24.digitalwalletsdk.domain.helper.TextStyleHelper;
import com.bill24.digitalwalletsdk.domain.model.base.BaseResponse;
import com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail.InstantPMDRequestModel;
import com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail.InstantPMDResponseModel;
import com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail.Transaction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetailActivity extends AppCompatActivity implements TransactionAdapter.OnTransactionItemClickListener {


    private ActivityAccountDetailBinding acaBinding;
    private String language;
    private String subTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        acaBinding=ActivityAccountDetailBinding.inflate(getLayoutInflater());
        setContentView(acaBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent=getIntent();
        if(intent!=null){
            language=intent.getStringExtra(Constant.LANGUAGE_KEY);
            subTitle=intent.getStringExtra(Constant.SUB_TITLE_KEY);
        }

        initBottomSheet();
        navigationBack();
        customIndicator();
        applyFontAppearance(this);
        fetchData(this);
        loadTranDetailBottomSheet();
    }


    private void navigationBack(){
        acaBinding.accountDetailNavigationBack.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
    }


    private void customIndicator(){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        // Set the radius for the top-left and top-right corners
        float[] radii = new float[] { 8f, 8f, 8f, 8f, 8f, 8f, 8f, 8f }; // top-left and top-right rounded
        drawable.setCornerRadii(radii);
        drawable.setColor(Color.GRAY); // Set the background color, e.g., white
        // Apply the drawable to the BottomSheet background
        acaBinding.tranIndicator.setBackground(drawable);
    }



    private void initBottomSheet(){
        //LinearLayoutCompat bottomSheet=findViewById(R.id.transacton_bottomsheet);
        BottomSheetBehavior<LinearLayoutCompat> bottomSheetBehavior=BottomSheetBehavior.from(acaBinding.transactonBottomsheet);
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int fortyPercentHeight = (int) (screenHeight * 0.45); // 40% of screen height
        // Set the peek height to 40% of the screen size
        bottomSheetBehavior.setPeekHeight(fortyPercentHeight);
        // Create a GradientDrawable for rounded corners on top left and top right only
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        // Set the radius for the top-left and top-right corners
        float[] radii = new float[] { 20f, 20f, 20f, 20f, 0f, 0f, 0f, 0f }; // top-left and top-right rounded
        drawable.setCornerRadii(radii);
        drawable.setColor(Color.parseColor(DefaultApperance.getCardColor())); // Set the background color, e.g., white
        // Apply the drawable to the BottomSheet background
        acaBinding.transactonBottomsheet.setBackground(drawable);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setDraggable(true);


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void applyFontAppearance(Context context){
        acaBinding.main.setBackgroundColor(Color.parseColor(DefaultApperance.getSecondaryColor()));
        acaBinding.accountDetailNavigationBack.setColorFilter(Color.parseColor(DefaultApperance.getLabelColor()));
        acaBinding.accountDetailBottomLine.setBackgroundColor(SetOpacityColor.opacityColor(DefaultApperance.getLabelColor(),0.3f));


        TextStyleHelper.styleTextView(acaBinding.accountDetailToolbarTitle,
                language.equals(Constant.ENGLISH_CODE)?  R.font.roboto_bold :R.font.battambang_bold,
                20,DefaultApperance.getLabelColor(),
                subTitle,context);

        CustomView.setRoundedBackgroundWithShadow(acaBinding.accountDetailContainer,DefaultApperance.getCardColor(),20,8);
        CustomView.setRoundedBackgroundWithShadow(acaBinding.accDetailBgIcon,DefaultApperance.getPrimaryColor(),20,8);
        CustomView.setRoundedBackgroundWithShadow(acaBinding.setDefaultContainer,DefaultApperance.getCardColor(),20,8);
        CustomView.setRoundedBackgroundWithShadow(acaBinding.disableAccContainer,DefaultApperance.getCardColor(),20,8);
        CustomView.setRoundedBackgroundWithShadow(acaBinding.buttonTopup,DefaultApperance.getPrimaryColor(),20,8);
        acaBinding.buttonTopup.setTextColor(Color.parseColor(DefaultApperance.getButtonTextColor()));

    }

    private void onBindRecyclerView(Context context, List<Transaction> items){
        acaBinding.tranItemRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        TransactionAdapter adapter=new TransactionAdapter(items,this);
        acaBinding.tranItemRecyclerView.setAdapter(adapter);

        acaBinding.tranItemRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void showProgressBar(){
        acaBinding.accDetailContainerProgressbar.setBackgroundColor(Color.parseColor(DefaultApperance.getSecondaryColor()));

    }
    private void hideProgressBar(){
        acaBinding.accDetailContainerProgressbar.setBackgroundColor(Color.TRANSPARENT);
        acaBinding.accDetailProgressbar.setVisibility(View.GONE);
    }

   private void fetchData(Context context){

        showProgressBar();

       ApiRequest apiRequest=new ApiRequest("2344");
       InstantPMDRequestModel model=new InstantPMDRequestModel("23455");
      Call<BaseResponse<InstantPMDResponseModel>> call=apiRequest.InstantPMDetail(model);

      call.enqueue(new Callback<BaseResponse<InstantPMDResponseModel>>() {
          @Override
          public void onResponse(Call<BaseResponse<InstantPMDResponseModel>> call, Response<BaseResponse<InstantPMDResponseModel>> response) {
              hideProgressBar();

              if(response.isSuccessful()){

                  InstantPMDResponseModel instantPMD=response.body().getData();
                  List<Transaction> transactions=response.body().getData().getTransactions();
                  onBindRecyclerView(context,transactions);

                  TextStyleHelper.styleTextView(
                          acaBinding.accDetailTitle,language.equals(Constant.KHMER_CODE) && !instantPMD.getTitleKh().isEmpty()?R.font.battambang_bold:R.font.roboto_bold,
                          16,
                          DefaultApperance.getLabelColor(),
                          language.equals(Constant.KHMER_CODE) && !instantPMD.getTitleKh().isEmpty()? instantPMD.getTitleKh()  :instantPMD.getTitle(),
                          context);

//                  TextStyleHelper.styleTextView(
//                          acaBinding.accDetailIdText,language.equals(Constant.KHMER_CODE) && !instantPMD.getTitleKh().isEmpty()?R.font.battambang_regular:R.font.roboto_regular,
//                          12,
//                          DefaultApperance.getLabelColor(),
//                          language.equals(Constant.KHMER_CODE) && !instantPMD.getTitleKh().isEmpty()? instantPMD.getTitleKh()  :instantPMD.getTitle(),
//                          context);


                  if(!instantPMD.isDefault()){
                      Drawable drawable=context.getDrawable(R.drawable.verified_24px);
                      if(drawable!=null){
                          acaBinding.accDetailDefaultIcon.setColorFilter(Color.parseColor(DefaultApperance.getPrimaryColor()));
                      }
                      TextStyleHelper.styleTextView(
                              acaBinding.accDetailDefault,language.equals(Constant.KHMER_CODE)?R.font.battambang_regular:R.font.roboto_regular,
                              12,
                              DefaultApperance.getPrimaryColor(),
                              language.equals(Constant.KHMER_CODE) ? Translate.SET_DEFAULT_KH  :Translate.SET_DEFAULT_EN,
                              context);

                      acaBinding.setDefaultContainer.setOnClickListener(view -> {
                          Log.d("AAA", "onResponse: ");
                      });
                  }

                  if(!instantPMD.isInactive()){
                      Drawable drawable=context.getDrawable(R.drawable.unpublished_24px);
                      if(drawable!=null){
                          acaBinding.accDetailDisableIcon.setColorFilter(Color.parseColor(DefaultApperance.getPrimaryColor()));
                      }
                      TextStyleHelper.styleTextView(
                              acaBinding.accDetailDisable,language.equals(Constant.KHMER_CODE)?R.font.battambang_regular:R.font.roboto_regular,
                              12,
                              DefaultApperance.getPrimaryColor(),
                              language.equals(Constant.KHMER_CODE) ? Translate.ENABLE_KH :Translate.ENABLE_EN,
                              context);

                  }
                  if(instantPMD.isInactive()){
                      Drawable drawable=context.getDrawable(R.drawable.unpublished_24px);
                      if(drawable!=null){
                          acaBinding.accDetailDisableIcon.setColorFilter(Color.parseColor(DefaultApperance.getTranPaid()));
                      }
                      TextStyleHelper.styleTextView(
                              acaBinding.accDetailDisable,language.equals(Constant.KHMER_CODE)?R.font.battambang_regular:R.font.roboto_regular,
                              12,
                              DefaultApperance.getTranPaid(),
                              language.equals(Constant.KHMER_CODE) ? Translate.DISABLE_KH :Translate.DISABLE_EN,
                              context);

                  }


              }
          }

          @Override
          public void onFailure(Call<BaseResponse<InstantPMDResponseModel>> call, Throwable throwable) {

          }
      });
   }

    private void  loadTranDetailBottomSheet(){
//        t.setOnClickListener(view -> {
//            TranDetailBottomSheetFragment bottomSheetFragment=new TranDetailBottomSheetFragment();
//            bottomSheetFragment.show(getSupportFragmentManager(),"trans");
//        });

    }

    @Override
    public void onItemClick(Transaction transaction) {
        Toast.makeText(this, ""+transaction.getAmount(), Toast.LENGTH_SHORT).show();
    }
}