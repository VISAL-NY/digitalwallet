package com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod;

import com.google.gson.annotations.SerializedName;

public class InstantPaymentMethodRequestModel {
    public void setCustomerSynCode(String customerSynCode) {
        this.customerSynCode = customerSynCode;
    }

    public void setFilterByStatus(String filterByStatus) {
        this.filterByStatus = filterByStatus;
    }

    public InstantPaymentMethodRequestModel(String customerSynCode, String filterByStatus) {
        this.customerSynCode = customerSynCode;
        this.filterByStatus = filterByStatus;
    }

    @SerializedName("customer_sync_code")
    private String customerSynCode;
    @SerializedName("filter_by_status")
    private String filterByStatus;
}
