package com.rohan.poc_mvp.communication.events;

/**
 * Created by rohan on 27/5/16.
 */
public class GetRepositoriesEvent {
    private final String username;

    public GetRepositoriesEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
