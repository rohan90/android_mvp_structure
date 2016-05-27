package com.rohan.poc_mvp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.ui.custom.ProgressDialog;

/**
 * Created by rohan on 26/5/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected final ProgressDialog progress;

    public BaseActivity() {
        progress = ProgressDialog.newInstance(R.string.progress_dialog_please_wait);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public synchronized void showProgress() {
        if (!progress.isAdded()) {
            progress.show(getFragmentManager(), null);
        }
    }

    public synchronized void hideProgress() {
        if (progress != null && progress.getActivity() != null) {
            progress.dismissAllowingStateLoss();
        }
    }

    protected void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void toastCustom(String message, int gravity, int duration) {
        Toast toast = Toast.makeText(this, message, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    public void snack(View view, String message, int notificationType, int duration) {
        Snackbar snackbar = Snackbar.make(view, message, duration)
                .setAction("Action", null);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this,notificationType));
        snackbar.show();
    }
}

