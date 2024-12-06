package com.bill24.digitalwalletsdk.data.local;

import com.bill24.digitalwalletsdk.domain.model.InstantSdkConfig.Appearance;
import com.bill24.digitalwalletsdk.domain.model.InstantSdkConfig.Mode;

import java.io.Serializable;

public class DefaultApperance implements Serializable {
    private static String primaryColor;
    private static String secondaryColor;
    private static String labelColor;
    private static String cardColor;
    private static String buttonTextColor;

    public static String getTranPaid() {
        return tranPaid;
    }

    public static void setTranPaid(String tranPaid) {
        DefaultApperance.tranPaid = tranPaid;
    }

    private static String tranPaid;

    public static String getPrimaryColor() {
        return primaryColor;
    }

    public static void setPrimaryColor(String primaryColor) {
        DefaultApperance.primaryColor = primaryColor;
    }

    public static String getSecondaryColor() {
        return secondaryColor;
    }

    public static void setSecondaryColor(String secondaryColor) {
        DefaultApperance.secondaryColor = secondaryColor;
    }

    public static String getLabelColor() {
        return labelColor;
    }

    public static void setLabelColor(String labelColor) {
        DefaultApperance.labelColor = labelColor;
    }

    public static String getCardColor() {
        return cardColor;
    }

    public static void setCardColor(String cardColor) {
        DefaultApperance.cardColor = cardColor;
    }

    public static String getButtonTextColor() {
        return buttonTextColor;
    }

    public static void setButtonTextColor(String buttonTextColor) {
        DefaultApperance.buttonTextColor = buttonTextColor;
    }

    public static void setAppearance(Appearance appearance,boolean isDarkMode){
        if (isDarkMode) {
            Mode darkMode = appearance.getDarkMode();

            primaryColor = darkMode.getPrimaryColor().isEmpty() ? "#1976D2" : darkMode.getPrimaryColor();
            secondaryColor = darkMode.getSecondaryColor().isEmpty() ? "#424242" : darkMode.getSecondaryColor();
            labelColor = darkMode.getLabelColor().isEmpty() ? "#BDBDBD" : darkMode.getLabelColor();
            cardColor = darkMode.getCardColor().isEmpty() ? "#212121" : darkMode.getCardColor();
            buttonTextColor = darkMode.getButtonTextColor().isEmpty() ? "#FFFFFF" : darkMode.getButtonTextColor();
            tranPaid=darkMode.getTranPaid().isEmpty()?"#FE608A":darkMode.getTranPaid();
        } else {
            Mode lightMode = appearance.getLightMode();
            primaryColor = lightMode.getPrimaryColor().isEmpty() ? "#2196F3" : lightMode.getPrimaryColor();
            secondaryColor = lightMode.getSecondaryColor().isEmpty() ? "#E0E0E0" : lightMode.getSecondaryColor();
            labelColor = lightMode.getLabelColor().isEmpty() ? "#757575" : lightMode.getLabelColor();
            cardColor = lightMode.getCardColor().isEmpty() ? "#FFFFFF" : lightMode.getCardColor();
            buttonTextColor = lightMode.getButtonTextColor().isEmpty() ? "#FFFFFF" : lightMode.getButtonTextColor();
            tranPaid=lightMode.getTranPaid().isEmpty()?"#FE608A":lightMode.getTranPaid();
        }
    }



}
