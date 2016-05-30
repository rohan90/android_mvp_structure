package com.rohan.poc_mvp.presenter;

import com.rohan.poc_mvp.communication.events.FetchedRepositoryListEvent;
import com.rohan.poc_mvp.model.domain.Repository;
import com.rohan.poc_mvp.presenter.interfaces.IRepositoryListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohan on 27/5/16.
 */
public class RepositoryListPresenterTest {

    private RepositoryListPresenter presenter;
    private IRepositoryListView viewMock;

    @Before
    public void setUp() {
        presenter = new RepositoryListPresenter();
        viewMock = Mockito.mock(IRepositoryListView.class);
        presenter.attachView(viewMock);
    }

    @Test
    public void shouldDelegateToViewForErrorWhenFetchedRepositories(){
        presenter.onFetchedRepositoryListEvent(new FetchedRepositoryListEvent(null,false,"Error"));
        Mockito.verify(viewMock).showMessage(Mockito.anyString());
    }

    @Test
    public void shouldDelegateToViewForShowRepositories(){
        presenter.onFetchedRepositoryListEvent(new FetchedRepositoryListEvent(getMockRepoList(),true,"Success"));
        Mockito.verify(viewMock).showRepositories(Mockito.anyList());
    }

    public List<Repository> getMockRepoList() {
        ArrayList<Repository> list = new ArrayList<>();
        list.add(new Repository());
        return list;
    }
}
