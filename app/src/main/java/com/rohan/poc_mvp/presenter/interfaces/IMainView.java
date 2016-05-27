package com.rohan.poc_mvp.presenter.interfaces;

/**
 * Created by rohan on 26/5/16.
 */
public interface IMainView extends MvpView {

    public void showMessage(String message);
    public void goToSearchScreen();
}
