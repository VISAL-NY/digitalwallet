package com.bill24.digitalwalletsdk.domain.model.InstantSdkConfig;

import com.google.gson.annotations.SerializedName;

public class Mode {
    @SerializedName("primary_color")
    private String primaryColor;
    @SerializedName("secondary_color")
    private String secondaryColor;
    @SerializedName("label_color")
    private String labelColor;
    @SerializedName("card_color")
    private String cardColor;
    @SerializedName("button_text_color")
    private String buttonTextColor;
    @SerializedName("tran_paid")
    private String tranPaid;

    public String getTranPaid() {
        return tranPaid;
    }

    public void setTranPaid(String tranPaid) {
        this.tranPaid = tranPaid;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public String getButtonTextColor() {
        return buttonTextColor;
    }

    public void setButtonTextColor(String buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }


}
