package com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod;

import com.google.gson.annotations.SerializedName;

public class PaymentMethod {
    @SerializedName("id")
    private String id;
    @SerializedName("logo")
    private String logo;
    @SerializedName("title")
    private String title;
    @SerializedName("title_kh")
    private String titleKh;
    @SerializedName("sub_title")
    private String subTitle;
    @SerializedName("sub_title_kh")
    private String subTitleKh;
    @SerializedName("is_default")
    private boolean isDefault;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitleKh() {
        return subTitleKh;
    }

    public void setSubTitleKh(String subTitleKh) {
        this.subTitleKh = subTitleKh;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
