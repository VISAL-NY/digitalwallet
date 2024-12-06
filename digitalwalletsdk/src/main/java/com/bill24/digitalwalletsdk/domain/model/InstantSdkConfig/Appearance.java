package com.bill24.digitalwalletsdk.domain.model.InstantSdkConfig;

import com.google.gson.annotations.SerializedName;

public class Appearance {
    @SerializedName("light_mode")
    private Mode lightMode;
    @SerializedName("dark_mode")
    private Mode darkMode;

    public Mode getLightMode() {
        return lightMode;
    }
    public void setLightMode(Mode lightMode) {
        this.lightMode = lightMode;
    }
    public Mode getDarkMode() {
        return darkMode;
    }
    public void setDarkMode(Mode darkMode) {
        this.darkMode = darkMode;
    }


}
