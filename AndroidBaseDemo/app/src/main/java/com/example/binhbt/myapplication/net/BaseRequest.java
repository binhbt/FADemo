package com.example.binhbt.myapplication.net;

import com.example.binhbt.myapplication.ui.DemoApplication;
import com.vn.fa.base.data.DataRepository;
import com.vn.fa.base.domain.Repository;
import com.vn.fa.base.net.FaRequest;

/**
 * Created by leobui on 10/30/2017.
 */

public class BaseRequest extends FaRequest {
    public BaseRequest(){
        //Add callback for all request
        addCallBack(new RequestCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onFinish(Object result) {
//                if (container != null){
//                    Toast.makeText(((Activity)container), "base callback", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }
    public static final String API_ENDPOINT = "http://tvbox.vegacdn.vn/tvbox/file/";
    @Override
    public boolean isLogging() {
        return true;
    }

    @Override
    public Repository getDataRepository() {
        return new DataRepository(getEndPoints(), DemoApplication.getCacheProviders());
    }

    @Override
    public boolean isCache() {
        return true;
    }

    @Override
    public String getBaseUrl() {
        return API_ENDPOINT;
    }
}
