package com.example.binhbt.myapplication.net;

import com.vn.vega.base.net.FARequest;

/**
 * Created by leobui on 10/30/2017.
 */

public class BaseRequest extends FARequest {
    public static final String API_ENDPOINT = "http://tvbox.vegacdn.vn/tvbox/file/";
    @Override
    public boolean isLogging() {
        return true;
    }

    @Override
    public String getBaseUrl() {
        return API_ENDPOINT;
    }
}
