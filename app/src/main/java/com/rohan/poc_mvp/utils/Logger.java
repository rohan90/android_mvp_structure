package com.rohan.poc_mvp.utils;

import android.util.Log;

import com.rohan.poc_mvp.application.Constants;

/**
 * Created by rohan on 12/3/16.
 */
public class Logger {
    private static final boolean isLoggerOn = true;
    private static final String TAG = Constants.LOGGER_TAG;

    public static void logError(String message) {
        if (!MiscUtils.isProduction())
            Log.e(TAG, message);
    }

    public static void logInfo(String message) {
        if (!MiscUtils.isProduction())
            Log.d(TAG, message);
    }
}
