package com.example.binhbt.myapplication.ui;

import com.example.binhbt.myapplication.net.EndPoints;
import com.vega.loader.FALoader;
import com.vn.vega.base.VegaApplication;

/**
 * Created by binhbt on 6/22/2016.
 */
public class DemoApplication extends VegaApplication{
    static volatile EndPoints singletonApi;
    @Override
    public void onCreate() {
        super.onCreate();
        FALoader.initialize(this);
    }
    public String getBaseUrl(){
        return EndPoints.API_ENDPOINT;
    }
    public static EndPoints getApi() {
        if (singletonApi == null) {
            synchronized (EndPoints.class) {
                if (singletonApi == null) {
                    if (instance == null){
                        instance = new DemoApplication();
                    }
                    singletonApi = instance.getApi(EndPoints.class);
                }
            }
        }
        return singletonApi;
    }

}
