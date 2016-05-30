package com.rohan.poc_mvp.communication;

import com.google.gson.Gson;
import com.rohan.poc_mvp.communication.api.ApiConstants;
import com.rohan.poc_mvp.communication.api.interfaces.IGithub;
import com.rohan.poc_mvp.model.domain.Repository;
import com.rohan.poc_mvp.utils.GsonUtils;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by rohan on 26/5/16.
 */
public class RestClient implements IGithub {
    private static final String URL = ApiConstants.BASE_URL;

    private static RestClient mRestClient;
    private static RestAdapter mRestAdapter;
    Gson gson = GsonUtils.getDateCompatibleGson();

    private RestClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public static RestClient getClient() {
        if (mRestClient == null)
            mRestClient = new RestClient();
        return mRestClient;
    }

    /**
     * linking of interface to rest-client
     */

//    @Override
//    public void getRepositories(String username,Callback<RepositoryListResponseDto> callback) {
//        IGithub service = mRestAdapter.create(IGithub.class);
//        service.getRepositories(username, callback);
//    }

    @Override
    public void getRepositories(String username,Callback<List<Repository>> callback) {
        IGithub service = mRestAdapter.create(IGithub.class);
        service.getRepositories(username, callback);
    }

}
