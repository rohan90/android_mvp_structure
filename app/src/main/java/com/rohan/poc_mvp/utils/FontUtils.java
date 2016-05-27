package com.rohan.poc_mvp.utils;

import android.app.Activity;
import android.view.View;

import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * Created by rohan on 24/3/16.
 */
public class FontUtils {
    public static void init(Activity activity) {
        TypefaceHelper.typeface(activity);
    }

    public static void init(View view) {
        TypefaceHelper.typeface(view);
    }
}
