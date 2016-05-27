package com.rohan.poc_mvp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.application.Constants;
import com.rohan.poc_mvp.presenter.interfaces.IMainView;
import com.rohan.poc_mvp.presenter.MainPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView {

    private MainPresenter presenter;

    @InjectView(R.id.et_github_username)
    EditText etGithubUserName;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initPresenter();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
    }

    private void initPresenter() {
        presenter = new MainPresenter();
        presenter.attachView(this);
    }

    @OnClick(R.id.btn_search)
    public void onSearchClicked(Button button){
        presenter.validateAndSearch(getEditTextEntry());
    }

    private String getEditTextEntry() {
        return etGithubUserName.getText().toString();
    }

    /**
     * Contracts...
     */

    @Override
    public void showMessage(String message) {
        snack(toolbar,message,R.color.notification_error,Snackbar.LENGTH_LONG);
    }

    @Override
    public void goToSearchScreen() {
        Intent intent = new Intent(this, RepositoryListActivity.class);
        intent.putExtra(Constants.BUNDLE_KEYS.GITHUB_USERNAME,getEditTextEntry());
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
