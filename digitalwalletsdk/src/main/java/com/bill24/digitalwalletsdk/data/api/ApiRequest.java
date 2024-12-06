package com.bill24.digitalwalletsdk.data.api;

import com.bill24.digitalwalletsdk.domain.model.InstantSdkConfig.InstantConfigResponseModel;
import com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType.AvailablePaymentMethodRequestModel;
import com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType.AvailablePaymentMethodTypeResponseModel;
import com.bill24.digitalwalletsdk.domain.model.base.BaseResponse;
import com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod.InstantPaymentMethodRequestModel;
import com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod.InstantPaymentMethodResponseModel;
import com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail.InstantPMDRequestModel;
import com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail.InstantPMDResponseModel;

import retrofit2.Call;


public class ApiRequest {

    private String refererKey;
    public ApiRequest(String refererKey){
        this.refererKey=refererKey;
    }

   public Call<BaseResponse<AvailablePaymentMethodTypeResponseModel>> getAvailablePaymentMethodType(AvailablePaymentMethodRequestModel model){
        return RetrofitClient.getInstance().apiClient().getAvailablePaymentMethodType("application/json",refererKey,"token",model);
    }

   public Call<BaseResponse<InstantPaymentMethodResponseModel>> getInstantPaymentMethod(InstantPaymentMethodRequestModel model){
        return  RetrofitClient.getInstance().apiClient().getInstantPaymentMethod("application/json",refererKey,"token",model);
    }

    public Call<BaseResponse<InstantConfigResponseModel>> getInstantConfig(){
        return  RetrofitClient.getInstance().apiClient().getInstantConfig("application/json",refererKey,"token");
    }

    public  Call<BaseResponse<InstantPMDResponseModel>> InstantPMDetail(InstantPMDRequestModel model){
        return  RetrofitClient.getInstance().apiClient().InstantPMDetail("application/json",refererKey,"token",model);

    }
}
