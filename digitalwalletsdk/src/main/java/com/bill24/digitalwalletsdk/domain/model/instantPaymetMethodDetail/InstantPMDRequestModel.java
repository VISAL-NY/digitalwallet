package com.bill24.digitalwalletsdk.domain.model.instantPaymetMethodDetail;

import com.google.gson.annotations.SerializedName;

public class InstantPMDRequestModel {
    public InstantPMDRequestModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("id")
    private String id;
}
