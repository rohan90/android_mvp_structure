package com.rohan.poc_mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.rohan.poc_mvp.communication.api.ApiConstants;

/**
 * Created by rohan on 24/3/16.
 */
public class MiscUtils {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void snack(View view, String message, int notificationType, int duration) {
        Snackbar snackbar = Snackbar.make(view, message, duration)
                .setAction("Action", null);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(view.getContext(), notificationType));
        snackbar.show();
    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean isLoggerOn() {
        //basically disable loggin on production release here..
        return true;
    }

    public static boolean isProduction(){
        if (ApiConstants.BASE_URL.equalsIgnoreCase(ApiConstants.PROD_URL))
            return true;

        return false;
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
