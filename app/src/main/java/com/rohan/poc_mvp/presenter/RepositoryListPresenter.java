package com.rohan.poc_mvp.presenter;

import com.rohan.poc_mvp.communication.bus.BusProvider;
import com.rohan.poc_mvp.communication.events.FetchedRepositoryListEvent;
import com.rohan.poc_mvp.communication.events.GetRepositoriesEvent;
import com.rohan.poc_mvp.presenter.interfaces.BasePresenter;
import com.rohan.poc_mvp.presenter.interfaces.IRepositoryListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by rohan on 27/5/16.
 */
public class RepositoryListPresenter implements BasePresenter<IRepositoryListView> {
    private IRepositoryListView view;
    private Bus mBus = BusProvider.getInstance();


    @Override
    public void attachView(IRepositoryListView view) {
        this.view = view;
        mBus.register(this);
    }

    @Override
    public void dettachView() {
        this.view = null;
        mBus.unregister(this);
    }

    public void getRepositories(String username) {
        mBus.post(new GetRepositoriesEvent(username));
    }

    @Subscribe
    public void onFetchedRepositoryListEvent(FetchedRepositoryListEvent event){
        if(!event.isStatus()){
            view.showMessage(event.getMessage());
        }else{
            view.showRepositories(event.getData());
        }
    }
}
