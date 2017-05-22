package com.rohan.poc_mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.application.App;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Created by rohan on 18/3/17.
 */
public class LanguageUtils {
    private static Map<String, String> languageToLocaleMap;
    private static Map<String, String> localeToLanguageMap;

    public static String getLocaleStringFromLangauge(String language) {
        if (languageToLocaleMap == null) {
            initMaps();
        }

        return languageToLocaleMap.get(language);
    }

    public static String getLanguageString(String locale) {
        if (localeToLanguageMap == null) {
            initMaps();
        }

        return localeToLanguageMap.get(locale);
    }

    public static void switchLocaleConfiguration(String localeString, Context context) {
        Locale locale = new Locale(localeString);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

    private static void initMaps() {
        languageToLocaleMap = new HashMap<>();
        languageToLocaleMap.put("ગુજરાતી", "gu");
        languageToLocaleMap.put("English", "en");
        languageToLocaleMap.put("हिंदी", "hi");
        languageToLocaleMap.put("मराठी", "mr");

        localeToLanguageMap = new HashMap<>();
        localeToLanguageMap.put("gu", "ગુજરાતી");
        localeToLanguageMap.put("en", "English");
        localeToLanguageMap.put("hi", "हिंदी");
        localeToLanguageMap.put("mr", "मराठी");

    }

    public static TypefaceCollection getTypefaceByLocale(String locale) {
        TypefaceCollection typeface;
        switch (locale) {
            case "gu":
                typeface = App.getEnglishTypeface();
                break;
            case "en":
                typeface = App.getEnglishTypeface();
                break;
            default:
                typeface = App.getEnglishTypeface();
                break;
        }

        return typeface;
    }

    public static Map<String, String> getRequestMap(SharedPreferencesUtil preferences) {

        Map<String, String> requestMap = new LinkedHashMap<>();
//        requestMap.put(ApiConstants.REQUEST_PARAMS.LANGUAGE, preferences.getSelectedLanguageI18nString());
//        requestMap.put(ApiConstants.REQUEST_PARAMS.FARMER_ID, MiscUtils.getFarmerId(App.getAppContext()));
        return requestMap;
    }

    public static Map<String, Object> getGenericRequestMap(SharedPreferencesUtil preferences) {

        Map<String, Object> requestMap = new LinkedHashMap<>();
//        requestMap.put(ApiConstants.REQUEST_PARAMS.LANGUAGE, preferences.getSelectedLanguageI18nString());
//        requestMap.put(ApiConstants.REQUEST_PARAMS.FARMER_ID, MiscUtils.getFarmerId(App.getAppContext()));
        return requestMap;
    }

    public static String getLanguage(SharedPreferencesUtil preferences) {
//        return preferences.getSelectedLanguageI18nString();
        return null;
    }

    public static String getTextInSepcifiedLocale(String localeString, int id, Context context) {
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        Locale savedLocale = conf.locale;
        conf.locale = new Locale(localeString); // whatever you want here
        res.updateConfiguration(conf, null); // second arg null means don't change

        // retrieve resources from desired locale
        String value = res.getString(id);

        // restore original locale
        conf.locale = savedLocale;
        res.updateConfiguration(conf, null);
        return value;
    }

    public static void setupViewLocale(Context context, View view, String locale) {
        switchLocaleConfiguration(locale, context);
        FontUtils.init(view);
    }

    public static void setupActivityLocale(Activity context, String locale) {
        switchLocaleConfiguration(locale, context);
        FontUtils.init(context);
    }

    public static void showDialogForLanguageChange(final Context context) {
        //should probably extract it out to strings array in resources..
        final String[] locales = {"en", "fr", "ja"};//TODO get this from string array?

        new AlertDialog.Builder(context)
                .setSingleChoiceItems(locales, 0, null)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        // Do something useful withe the position of the selected radio button
                        restartInLocale(new Locale(locales[selectedPosition]),context);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private static void restartInLocale(Locale locale,Context context) {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = context.getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
//        ((HomeActivity)context).recreate();
    }
}
