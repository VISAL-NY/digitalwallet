package com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail;

import com.google.gson.annotations.SerializedName;

public class Transaction {
    @SerializedName("tran_type")
    private String tranType;
    @SerializedName("tran_id")
    private String tranID;
    @SerializedName("amount")
    private double amount;
    @SerializedName("currency")
    private String currency;
    @SerializedName("tran_date")
    private String tranDate;
    @SerializedName("description")
    private String description;

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranID() {
        return tranID;
    }

    public void setTranID(String tranID) {
        this.tranID = tranID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
