package com.bill24.digitalwalletsdk.domain.model.InstantSdkConfig;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstantConfigResponseModel {
    @SerializedName("appearance")
    private Appearance appearance;
    @SerializedName("currency")
    private List<String> currency;

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public List<String> getCurrency() {
        return currency;
    }

    public void setCurrency(List<String> currency) {
        this.currency = currency;
    }


}
