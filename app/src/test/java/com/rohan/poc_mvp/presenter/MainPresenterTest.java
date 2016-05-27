package com.rohan.poc_mvp.presenter;

import com.rohan.poc_mvp.presenter.interfaces.IMainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by rohan on 26/5/16.
 */
public class MainPresenterTest {

    private MainPresenter presenter;
    private IMainView viewMock;

    @Before
    public void setUp(){
        presenter = new MainPresenter();
        viewMock = Mockito.mock(IMainView.class);
        presenter.attachView(viewMock);
    }

    @Test
    public void shouldGoToRepositoriesListScreen(){
        presenter.validateAndSearch("username");
        Mockito.verify(viewMock).goToSearchScreen();
    }

    @Test
    public void shouldShowErrorMessage(){
        presenter.validateAndSearch("");
        Mockito.verify(viewMock).showMessage(Mockito.anyString());
    }
}
