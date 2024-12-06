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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("instant_payment_sdk/available_payment_method_type")
    Call<BaseResponse<AvailablePaymentMethodTypeResponseModel>> getAvailablePaymentMethodType(
            @Header("Content-Type") String contentType,
            @Header("Referer-Key") String refererKey,
            @Header("token") String token,
            @Body AvailablePaymentMethodRequestModel model
            );

    @POST("instant_payment_sdk/instant_payment_methods")
    Call<BaseResponse<InstantPaymentMethodResponseModel>> getInstantPaymentMethod(
            @Header("Content-Type") String contentType,
            @Header("Referer-Key") String refererKey,
            @Header("token") String token,
            @Body InstantPaymentMethodRequestModel model
            );

    @GET("instant_payment_sdk/configs")
    Call<BaseResponse<InstantConfigResponseModel>> getInstantConfig(
            @Header("Content-Type") String contentType,
            @Header("Referer-Key") String refererKey,
            @Header("token") String token
    );
    @POST("instant_payment_sdk/payment_method/detail")
    Call<BaseResponse<InstantPMDResponseModel>> InstantPMDetail(
            @Header("Content-Type") String contentType,
            @Header("Referer-Key") String refererKey,
            @Header("token") String token,
            @Body InstantPMDRequestModel model
    );
}
