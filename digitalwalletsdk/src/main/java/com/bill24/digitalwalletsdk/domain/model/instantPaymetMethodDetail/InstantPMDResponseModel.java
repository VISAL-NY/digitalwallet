package com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstantPMDResponseModel {
   @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("account_no")
    private String accountNo;
    @SerializedName("account_name")
    private String accountName;
    @SerializedName("account_name_kh")
    private String accountNameKh;
    @SerializedName("amount")
    private double amount;
    @SerializedName("currency")
    private String currency;
    @SerializedName("logo")
    private String logo;
    @SerializedName("title")
    private String title;
    @SerializedName("title_kh")
    private String titleKh;
    @SerializedName("is_default")
    private boolean isDefault;
    @SerializedName("in_active")
    private boolean inactive;
    @SerializedName("transactions")
    private List<Transaction> transactions;
    @SerializedName("pagination")
    private Pagination pagination;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNameKh() {
        return accountNameKh;
    }

    public void setAccountNameKh(String accountNameKh) {
        this.accountNameKh = accountNameKh;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleKh() {
        return titleKh;
    }

    public void setTitleKh(String titleKh) {
        this.titleKh = titleKh;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


}
