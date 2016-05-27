package com.rohan.poc_mvp.communication.events;

import com.rohan.poc_mvp.model.Repository;

import java.util.List;

/**
 * Created by rohan on 27/5/16.
 */
public class FetchedRepositoryListEvent {
    private final List<Repository> data;
    private final boolean status;
    private final String message;

    public FetchedRepositoryListEvent(List<Repository> data, boolean status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public List<Repository> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
