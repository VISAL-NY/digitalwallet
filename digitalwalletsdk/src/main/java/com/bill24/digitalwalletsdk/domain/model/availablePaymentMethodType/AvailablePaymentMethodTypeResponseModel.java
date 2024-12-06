package com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailablePaymentMethodTypeResponseModel {


    public List<AvailablePaymentMethodType> getAvailablePaymentMethodType() {
        return availablePaymentMethodType;
    }

    public void setAvailablePaymentMethodType(List<AvailablePaymentMethodType> availablePaymentMethodType) {
        this.availablePaymentMethodType = availablePaymentMethodType;
    }

    @SerializedName("available_payment_method_type")
    private List<AvailablePaymentMethodType> availablePaymentMethodType;
}
