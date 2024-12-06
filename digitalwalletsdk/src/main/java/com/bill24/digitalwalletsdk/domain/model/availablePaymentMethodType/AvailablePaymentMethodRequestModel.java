package com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType;

import com.google.gson.annotations.SerializedName;

public class AvailablePaymentMethodRequestModel {

    public AvailablePaymentMethodRequestModel(String customerSynCode) {
        this.customerSynCode = customerSynCode;
    }

    @SerializedName("customer_syn_code")
        private String customerSynCode;
        public void setCustomerSynCode(String customerSynCode) {
            this.customerSynCode = customerSynCode;
        }

}
