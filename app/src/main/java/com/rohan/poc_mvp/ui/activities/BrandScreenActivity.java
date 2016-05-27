package com.rohan.poc_mvp.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.utils.FontUtils;

/**
 * Created by rohan on 27/5/16.
 */
public class BrandScreenActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        makeFullScreen();
        setContentView(R.layout.activity_brand_screen);
        FontUtils.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, 1000);
    }

    private void makeFullScreen() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (Build.VERSION.SDK_INT > 20)
            window.setStatusBarColor(getResources().getColor(R.color.white));
    }

    private void nextActivity() {
        Intent intent = MainActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // back is not allowed when on splash screen TODO should i show message?
    }
}
