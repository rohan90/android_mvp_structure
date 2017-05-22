package com.rohan.poc_mvp.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.rohan.poc_mvp.communication.EventManager;
import com.rohan.poc_mvp.communication.bus.BusProvider;
import com.squareup.otto.Bus;

/**
 * Created by rohan on 26/5/16.
 */
public class App extends Application {
    private static Context context;
    private static TypefaceCollection typeface;

    private EventManager mManager;
    private Bus mBus = BusProvider.getInstance();

    public static Context getAppContext() {
        return App.context;
    }

    public static TypefaceCollection getTypeface() {
        return typeface;
    }

    public static TypefaceCollection getEnglishTypeface() {
        return typeface;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initTypefaces();

        App.context = getApplicationContext();

        initEventManager();
    }

    private void initEventManager() {
        mManager = new EventManager(this, mBus);
        mBus.register(mManager);
        mBus.register(this);
    }

    private void initTypefaces() {
        typeface = new TypefaceCollection.Builder()
        .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/gotham/Gotham-Rounded-Book.ttf"))
        .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/gotham/Gotham-Rounded-Bold.ttf"))
        .create();
        TypefaceHelper.init(typeface);
    }
}
