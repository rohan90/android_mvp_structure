package com.rohan.poc_mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


import com.rohan.poc_mvp.application.Constants;

import static com.squareup.okhttp.internal.Internal.instance;

public class SharedPreferencesUtil {

    private static SharedPreferencesUtil sharedPreferencesUtil;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPreferencesUtil() {
        // TODO Auto-generated constructor stub
    }

    private static void initializeSharedPref(Context context) {
        sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.sharedPreferences = context.getSharedPreferences(
                Constants.PREFERENCES_FILENAME, Activity.MODE_PRIVATE);
        sharedPreferencesUtil.editor = sharedPreferencesUtil.sharedPreferences
                .edit();
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (sharedPreferencesUtil == null) {
            initializeSharedPref(context);
        }
        return sharedPreferencesUtil;
    }

    public synchronized boolean saveData(String key, String value) {
        Logger.logInfo("saving " + key + " = " + value);
        editor.putString(key, value);
        return editor.commit();
    }

    public synchronized boolean saveData(String key, boolean value) {
        editor.putBoolean(key, value);
        Logger.logInfo("saving " + key + " = " + value);
        return editor.commit();
    }

    public synchronized boolean saveData(String key, long value) {
        editor.putLong(key, value);
        return editor.commit();
    }


    public synchronized boolean saveData(String key, float value) {
        editor.putFloat(key, value);
        return editor.commit();
    }

    public synchronized boolean saveData(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    public synchronized boolean removeData(String key) {
        editor.remove(key);
        return editor.commit();
    }

    public synchronized Boolean getData(String key, boolean defaultValue) {
        Logger.logInfo(sharedPreferences.getBoolean(key, defaultValue) + "");
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public synchronized String getData(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public synchronized float getData(String key, float defaultValue) {

        return sharedPreferences.getFloat(key, defaultValue);
    }

    public synchronized int getData(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public synchronized long getData(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public synchronized void deleteAllData() {
        sharedPreferencesUtil = null;
        editor.clear();
        editor.commit();
    }

    public String getSelectedLanguageI18nString() {
        if (instance != null) {
            return sharedPreferences.getString(Constants.BUNDLE_KEYS.SELECTED_LOCALE, Constants.DEFAULT_LOCALE);
        }
        return Constants.DEFAULT_LOCALE;
    }
}