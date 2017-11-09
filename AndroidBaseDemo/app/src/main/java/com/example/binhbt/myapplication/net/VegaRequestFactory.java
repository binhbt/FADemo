package com.example.binhbt.myapplication.net;

import com.vn.fa.base.net.FARequest;

/**
 * Created by leobui on 10/30/2017.
 */

public abstract class VegaRequestFactory {
    public VegaRequestFactory(){
        init();
    }
    public abstract void init();
    //public abstract FARequest getRequest(T vegaRequestType);
    public FARequest getRequest(String className){
        try {
            Class<?> clazz = Class.forName(className);
            return (FARequest)clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
