package com.example.binhbt.myapplication.net;

import com.vn.vega.base.net.FARequest;

/**
 * Created by leobui on 10/30/2017.
 */

public abstract class VegaRequestFactory<T> {
    public VegaRequestFactory(){
        init();
    }
    public abstract void init();
    public abstract FARequest getRequest(T vegaRequestType);
}
