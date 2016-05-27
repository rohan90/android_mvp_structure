package com.rohan.poc_mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rohan on 27/5/16.
 */
public class RestResponse {
    @SerializedName("errorMessage")
    private String errorMessage;

    @SerializedName("status")
    private boolean status;

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isStatus() {
        return status;
    }
}
