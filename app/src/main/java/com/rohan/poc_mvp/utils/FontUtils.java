package com.rohan.poc_mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.rohan.poc_mvp.application.Constants;

/**
 * Created by rohan on 24/3/16.
 */
public class FontUtils {
//    public static void init(Activity activity) {
//        TypefaceCollection typefaceByLanguageId = App.getTypeface();
//        TypefaceHelper.typeface(activity, typefaceByLanguageId);
//    }
//
//    public static void init(View view) {
//        TypefaceCollection typefaceByLanguageId = App.getTypeface();
//        TypefaceHelper.typeface(view, typefaceByLanguageId);
//    }

    public static void init(Activity activity) {
        TypefaceCollection typefaceByLanguageId = LanguageUtils.getTypefaceByLocale(SharedPreferencesUtil.getInstance(activity).getSelectedLanguageI18nString());
        TypefaceHelper.typeface(activity, typefaceByLanguageId);
    }

    public static void init(View view) {
        TypefaceCollection typefaceByLanguageId = LanguageUtils.getTypefaceByLocale(SharedPreferencesUtil.getInstance(view.getContext()).getSelectedLanguageI18nString());
        TypefaceHelper.typeface(view, typefaceByLanguageId);
    }

    public static Typeface getFontIconTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), Constants.PATH_TTF_FILE);
    }

    public static Drawable getFontIconDrawable(Context context, String icon) {
        return new FontIconDrawable(context, icon, getFontIconTypeface(context)).actionBarSize();
    }

    public static Drawable getFontIconDrawable(Context context, String icon, int size) {
        return new FontIconDrawable(context, icon, getFontIconTypeface(context)).sizeDp(size);
    }

    public static Drawable getFontIconDrawable(Context context, String icon, int size, int colorRes) {
        return new FontIconDrawable(context, icon, getFontIconTypeface(context))
                .colorRes(colorRes)
                .sizeDp(size);
    }
}
