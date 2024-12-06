package com.bill24.digitalwalletsdk.domain.helper;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;

public class CustomView {
    public static void setRoundedBackground(View view,String color,float cornerRadius){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(Color.parseColor(color));  // Set background color
        drawable.setCornerRadius(cornerRadius); // Set corner radius for roundness
        view.setBackground(drawable);
    }

    public  static void setRoundedBackgroundWithShadow(View view,String color,float cornerRadius,float shadowRadius){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(Color.parseColor(color));  // Set background color
        drawable.setCornerRadius(cornerRadius); // Set corner radius for roundness

        // Create a ShapeDrawable for the bottom shadow
        GradientDrawable shadowDrawable = new GradientDrawable();
        shadowDrawable.setShape(GradientDrawable.RECTANGLE);
        shadowDrawable.setColor(Color.parseColor("#000000"));  // Shadow color (semi-transparent black)
        shadowDrawable.setCornerRadius(cornerRadius); // Same corner radius for consistency
        shadowDrawable.setSize(view.getWidth(), (int) shadowRadius);  // Set shadow height
        // Combine the drawable and shadow in a LayerDrawable
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shadowDrawable, drawable});
        // Set the background to the LayerDrawable
        view.setBackground(layerDrawable);
    }
    public static void setRoundedBackgroundWithShadow(View view, String color, float cornerRadius, float shadowRadius, float opacity) {
        // Create the background drawable (for the overlay)
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(setOpacity(Color.parseColor(color), opacity));  // Set background color with opacity
        drawable.setCornerRadius(cornerRadius); // Set corner radius for roundness

        // Create a shadow drawable with semi-transparent black color
        GradientDrawable shadowDrawable = new GradientDrawable();
        shadowDrawable.setShape(GradientDrawable.RECTANGLE);
        shadowDrawable.setColor(setOpacity(Color.parseColor("#000000"), opacity));  // Shadow color with opacity
        shadowDrawable.setCornerRadius(cornerRadius); // Same corner radius for consistency

        // Set the shadow size (make it larger than the view for better visibility)
        shadowDrawable.setSize(view.getWidth() + (int) shadowRadius * 2, view.getHeight() + (int) shadowRadius);

        // Apply padding to ensure the shadow is visible around the view
        view.setPadding((int) shadowRadius, (int) shadowRadius, (int) shadowRadius, (int) shadowRadius);

        // Combine the shadow and background drawable using LayerDrawable
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shadowDrawable, drawable});

        // Set the background of the overlay layout with the combined drawable
        view.setBackground(layerDrawable);

        // Optional: Adjust the elevation to make sure the shadow appears
        view.setElevation(8f); // Set elevation (you can adjust this value)
    }
    // Helper method to set opacity for any color
    private static int setOpacity(int color, float opacity) {
        int alpha = (int) (opacity * 255); // Convert opacity from float to alpha value
        return (color & 0x00FFFFFF) | (alpha << 24); // Set the alpha channel of the color
    }

}
