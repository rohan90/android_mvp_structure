package com.rohan.poc_mvp.presenter.interfaces;

/**
 * Created by rohan on 26/5/16.
 */
public interface BasePresenter<V> {

    public void attachView(V view);
    public void dettachView();
}
