package com.rohan.poc_mvp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.application.Constants;
import com.rohan.poc_mvp.model.Repository;
import com.rohan.poc_mvp.presenter.RepositoryPresenter;
import com.rohan.poc_mvp.presenter.interfaces.IRepositoryListView;
import com.rohan.poc_mvp.ui.adapters.RepositoryAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RepositoryListActivity extends BaseActivity implements IRepositoryListView {

    private RepositoryPresenter presenter;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.tv_error_no_repos)
    TextView tvErrorMessage;

    @InjectView(R.id.rv_repositories)
    RecyclerView rvRepositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        ButterKnife.inject(this);

        initToolbar();
        initPresenter();
        initRecyclerView();
        loadRepositories();
    }

    private void initRecyclerView() {
        RepositoryAdapter adapter = new RepositoryAdapter();
        adapter.setCallback(new RepositoryAdapter.Callback() {
            @Override
            public void onItemClick(Repository repository) {
//                startActivity(RepositoryActivity.newIntent(RepositoryListActivity.this, repository));
            }
        });
        rvRepositories.setAdapter(adapter);
        rvRepositories.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadRepositories() {
        String username = getIntent().getStringExtra(Constants.BUNDLE_KEYS.GITHUB_USERNAME);
        setTitle("Repos for : "+username); //TODO take this out [strings and seperate init]
        presenter.getRepositories(username);
        showProgress();
    }

    private void initPresenter() {
        presenter = new RepositoryPresenter();
        presenter.attachView(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        presenter.dettachView();
        super.onDestroy();
    }

    /**
     *
     * Contract...
     */

    @Override
    public void showRepositories(List<Repository> repositories) {
        hideProgress();
        rvRepositories.setVisibility(View.VISIBLE);
        tvErrorMessage.setVisibility(View.GONE);

        RepositoryAdapter adapter = (RepositoryAdapter) rvRepositories.getAdapter();
        adapter.setRepositories(repositories);
        adapter.notifyDataSetChanged();
        rvRepositories.requestFocus();
    }

    @Override
    public void showMessage(String message) {
        hideProgress();
        tvErrorMessage.setVisibility(View.VISIBLE);
        rvRepositories.setVisibility(View.GONE);
        snack(toolbar,message,R.color.notification_error, Snackbar.LENGTH_LONG);
    }

    @Override
    public Context getContext() {
        return this;
    }


    /**
     *
     */
    public static Intent newIntent(Context context, String username) {
        Intent intent = new Intent(context, RepositoryListActivity.class);
        intent.putExtra(Constants.BUNDLE_KEYS.GITHUB_USERNAME, username);
        return intent;
    }
}
