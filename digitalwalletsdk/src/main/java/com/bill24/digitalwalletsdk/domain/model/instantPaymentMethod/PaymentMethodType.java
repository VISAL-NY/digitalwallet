package com.bill24.digitalwalletsdk.domain.model.instantPaymentMethod;

import com.google.gson.annotations.SerializedName;

public class PaymentMethodType {
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

    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("name_kh")
    private String nameKh;
}
