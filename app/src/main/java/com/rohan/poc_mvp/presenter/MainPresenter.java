package com.rohan.poc_mvp.presenter;

import com.rohan.poc_mvp.presenter.interfaces.BasePresenter;
import com.rohan.poc_mvp.presenter.interfaces.IMainView;

/**
 * Created by rohan on 26/5/16.
 */
public class MainPresenter implements BasePresenter<IMainView> {
    IMainView view;

    @Override
    public void attachView(IMainView view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

    public void validateAndSearch(String username) {
        if(username.isEmpty()){
            view.showMessage("Please enter a username");
        }else {
            view.goToSearchScreen();
        }
    }
}
