package com.app.elixir.brewz_rock_cafe.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;

import com.app.elixir.brewz_rock_cafe.R;


public class MtplDrawables {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setStrokeGrayandFillWhite(View v, Activity activity) {

        GradientDrawable state1 = new GradientDrawable();
        state1.setCornerRadius(activity.getResources().getDimension(R.dimen.margin_1dp));
        state1.setColor(activity.getResources().getColor(R.color.color_forgotdialog_editbox));
        state1.setStroke((int) activity.getResources().getDimension(R.dimen.margin_1dp), activity.getResources().getColor(R.color.color_edittext_border));
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{}, state1);


        int sdk = Build.VERSION.SDK_INT;
        if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundDrawable(drawable);
        } else {
            v.setBackground(drawable);
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBlueLightBlue(View v, Activity activity) {
        GradientDrawable state1 = new GradientDrawable();
        // state1.setCornerRadius(activity.getResources().getDimension(R.dimen.margin_2dp));
        state1.setColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        // state1.setStroke((int) activity.getResources().getDimension(R.dimen.margin_1dp), activity.getResources().getColor(R.color.button_background));
        GradientDrawable state2 = new GradientDrawable();
        //   state2.setCornerRadius(activity.getResources().getDimension(R.dimen.margin_2dp));
        state2.setColor(activity.getResources().getColor(R.color.colorPrimary));
        // state2.setStroke((int) activity.getResources().getDimension(R.dimen.margin_1dp), activity.getResources().getColor(R.color.color_header_bg));
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, state2);
        drawable.addState(new int[]{android.R.attr.state_focused}, state2);
        drawable.addState(new int[]{}, state1);


        int sdk = Build.VERSION.SDK_INT;
        if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundDrawable(drawable);
        } else {
            v.setBackground(drawable);
        }
    }
}
