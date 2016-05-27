package com.rohan.poc_mvp.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.application.Constants;
import com.rohan.poc_mvp.model.Repository;
import com.rohan.poc_mvp.presenter.RepositoryPresenter;
import com.rohan.poc_mvp.presenter.interfaces.IRepositoryListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RepositoryListActivity extends BaseActivity implements IRepositoryListView {

    private RepositoryPresenter presenter;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        ButterKnife.inject(this);

        initToolbar();
        initPresenter();
        loadRepositories();
    }

    private void loadRepositories() {
        String username = getIntent().getStringExtra(Constants.BUNDLE_KEYS.GITHUB_USERNAME);
        presenter.getRepositories(username);
    }

    private void initPresenter() {
        presenter = new RepositoryPresenter();
        presenter.attachView(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     *
     * Contract...
     */

    @Override
    public void showRepositories(List<Repository> repositories) {

    }

    @Override
    public void showMessage(String message) {
        snack(toolbar,message,R.color.notification_error, Snackbar.LENGTH_LONG);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
