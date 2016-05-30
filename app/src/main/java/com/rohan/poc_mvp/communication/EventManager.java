package com.rohan.poc_mvp.communication;

import android.content.Context;

import com.rohan.poc_mvp.communication.events.FetchedRepositoryListEvent;
import com.rohan.poc_mvp.communication.events.GetRepositoriesEvent;
import com.rohan.poc_mvp.model.domain.Repository;
import com.rohan.poc_mvp.model.domain.RestResponse;
import com.rohan.poc_mvp.utils.GsonUtils;
import com.rohan.poc_mvp.utils.Logger;
import com.rohan.poc_mvp.utils.MiscUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by rohan on 26/5/16.
 */
public class EventManager {
    private Context mContext;
    private Bus mBus;
    private RestClient mRestClient;

    public EventManager(Context mContext, Bus mBus) {
        this.mContext = mContext;
        this.mBus = mBus;
        this.mRestClient = RestClient.getClient();
    }

    /**
     * listening to events
     */

//    @Subscribe
//    public void onGetRepositoriesEvent(GetRepositoriesEvent event) {
//        Callback<RepositoryListResponseDto> callback = new Callback<RepositoryListResponseDto>() {
//
//            @Override
//            public void success(RepositoryListResponseDto restResponse, Response response) {
//                Logger.logInfo("response:" + response.toString());
//                Logger.logInfo("repos:" + restResponse.toString());
//                mBus.post(new FetchedRepositoryListEvent(restResponse.getData(), true, ""));
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                String extraMessage = getExtraMessage(error);
//                String errorMessage = getErrorMessage(error);
//                mBus.post(new FetchedRepositoryListEvent(null, false, errorMessage + extraMessage));
//            }
//        };
//        mRestClient.getRepositories(event.getUsername(),callback);
//    }

    @Subscribe
    public void onGetRepositoriesEvent(GetRepositoriesEvent event) {
        Callback<List<Repository>> callback = new Callback<List<Repository>>() {

            @Override
            public void success(List<Repository> restResponse, Response response) {
                Logger.logInfo("response:" + response.toString());
                Logger.logInfo("repos:" + restResponse.toString());
                mBus.post(new FetchedRepositoryListEvent(restResponse, true, ""));
            }

            @Override
            public void failure(RetrofitError error) {
                String extraMessage = getExtraMessage(error);
                String errorMessage = getErrorMessage(error);
                mBus.post(new FetchedRepositoryListEvent(null, false, errorMessage + extraMessage));
            }
        };
        mRestClient.getRepositories(event.getUsername(), callback);
    }


    /**
     * Helper method to parse REST contract response incase of error;
     */

    private String getExtraMessage(RetrofitError error) {
        String extraMessage = "";
        try {

            String responseBody = new String(((TypedByteArray) error.getResponse().getBody()).getBytes()).toString();
            RestResponse response = GsonUtils.getObjectFromJson(responseBody, RestResponse.class);
            extraMessage = response.getErrorMessage();
        } catch (Exception e) {
            // you know when non server related shit happens.
        }
        return " " + extraMessage;
    }

    private String getErrorMessage(RetrofitError error) {
        if (!MiscUtils.isProduction()) {
            return error.getMessage();
        }
        return "";
    }

}
