package com.rohan.poc_mvp.communication.api.interfaces;

import com.rohan.poc_mvp.model.domain.Repository;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by rohan on 27/5/16.
 */
public interface IGithub {

//    @GET("/users/{username}/repos")
//    void getRepositories(@Path("username") String username, Callback<RepositoryListResponseDto> callback);

    @GET("/users/{username}/repos")
    void getRepositories(@Path("username") String username, Callback<List<Repository>> callback);
}
