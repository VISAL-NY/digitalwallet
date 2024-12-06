package com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstantPaymentMethodResponseModel {
    @SerializedName("customer_syn_code")
    private String customerSynCode;
    @SerializedName("customer_code")
    private String customerCode;
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("payment_method")
    private List<PaymentMethod> paymentMethod;
    @SerializedName("payment_method_type")
    private List<PaymentMethodType> paymentMethodType;

    @Nullable
    public String getCustomerSynCode() {
        return customerSynCode;
    }

    @Nullable
    public String getCustomerCode() {
        return customerCode;
    }

    @Nullable
    public String getCustomerName() {
        return customerName;
    }

    @Nullable
    public List<PaymentMethod> getPaymentMethod() {
        return paymentMethod;
    }

    @Nullable
    public List<PaymentMethodType> getPaymentMethodType() {
        return paymentMethodType;
    }



}
