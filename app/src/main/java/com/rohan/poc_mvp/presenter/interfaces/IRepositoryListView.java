package com.rohan.poc_mvp.presenter.interfaces;

import com.rohan.poc_mvp.model.Repository;

import java.util.List;

/**
 * Created by rohan on 27/5/16.
 */
public interface IRepositoryListView extends MvpView {

    public void showRepositories(List<Repository> repositories);
    public void showMessage(String message);
}
