package com.rohan.poc_mvp.utils;

import android.util.Log;

/**
 * Created by rohan on 12/3/16.
 */
public class Logger {
    private static final boolean isLoggerOn = true;
    private static final String TAG = "asPOClogs";

    public static void logError(String message) {
        if (MiscUtils.isLoggerOn())
            Log.e(TAG, message);
    }

    public static void logInfo(String message) {
        if (MiscUtils.isLoggerOn())
            Log.d(TAG, message);
    }
}
