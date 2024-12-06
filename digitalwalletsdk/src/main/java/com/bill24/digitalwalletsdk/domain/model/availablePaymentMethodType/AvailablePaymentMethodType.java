package com.bill24.digitalwalletsdk.domain.model.availablePaymentMethodType;

import com.google.gson.annotations.SerializedName;

public class AvailablePaymentMethodType {
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("name_kh")
    private String nameKh;
    @SerializedName("logo")
    private String logo;
    @SerializedName("is_active")
    private boolean isActive;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKh() {
        return nameKh;
    }

    public void setNameKh(String nameKh) {
        this.nameKh = nameKh;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
