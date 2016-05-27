package com.rohan.poc_mvp.communication.api;

/**
 * Created by rohan on 26/5/16.
 */
public class ApiConstants {

    public static final String PROD_URL = "http://url:port";
    public static final String LOCAL_URL = "http://192.168.1.2:8090";
    public static final String DEV_URL = "https://api.github.com/";

    private static final String API_VERSION = "/api/v1";

    //ACTUAL URL
    public static final String BASE_URL = DEV_URL /*+ API_VERSION*/;

    public static final int CONNECTION_TIMEOUT = 10;

    public class REST_API {
        public static final String BASE = "/products/";
        public static final String GET_REPOS = BASE + "all";
    }
}
