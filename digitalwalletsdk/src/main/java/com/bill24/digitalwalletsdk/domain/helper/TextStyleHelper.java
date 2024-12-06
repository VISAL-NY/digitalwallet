package com.bill24.digitalwalletsdk.domain.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

public class TextStyleHelper {
    public static void styleTextView(AppCompatTextView textView,
                                     int fontResId,
                                     float fontSizeSp,
                                     String colorHex,
                                     String text,
                                     Context context) {
        try {
            Typeface typeface = ResourcesCompat.getFont(context, fontResId);
            textView.setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace(); // Handle missing font gracefully
        }
        // Set font size
        textView.setText(text);
        textView.setTextSize(fontSizeSp);
        // Set font color
        try {
            int color = Color.parseColor(colorHex);
            textView.setTextColor(color);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
