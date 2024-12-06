package com.bill24.digitalwalletsdk.domain.model;

public class Currency {
    private String currencyIcon;
    private String currencyCode;

    public Currency(String currencyCode, String currencyName) {
        this.currencyIcon = currencyCode;
        this.currencyCode = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyIcon() {
        return currencyIcon;
    }

    public void setCurrencyIcon(String currencyIcon) {
        this.currencyIcon = currencyIcon;
    }


}
