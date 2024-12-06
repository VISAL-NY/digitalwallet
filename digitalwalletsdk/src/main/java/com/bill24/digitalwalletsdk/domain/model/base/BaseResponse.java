package com.bill24.digitalwalletsdk.domain.model.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T>{

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage_kh() {
        return message_kh;
    }

    public T getData() {
        return data;
    }

    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("message_kh")
    private String message_kh;
    @SerializedName("data")
    private T data;

}
