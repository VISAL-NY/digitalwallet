package com.bill24.digitalwalletsdk.domain.helper;

import android.graphics.Color;

public class SetOpacityColor {

    public static int opacityColor(String colorHex, float opacityFraction) {
        // Validate opacity fraction (must be between 0.0 and 1.0)
        if (opacityFraction < 0.0f || opacityFraction > 1.0f) {
            throw new IllegalArgumentException("Opacity fraction must be between 0.0 and 1.0");
        }
        // Parse the base color
        int color = Color.parseColor(colorHex);
        // Compute the alpha value
        int alpha = Math.round(opacityFraction * 255);
        // Return the color with the updated alpha
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }
}
