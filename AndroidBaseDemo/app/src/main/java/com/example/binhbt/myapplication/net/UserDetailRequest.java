package com.example.binhbt.myapplication.net;

import com.example.binhbt.myapplication.model.User;
import com.example.binhbt.myapplication.model.VersionApp;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.net.request.RequestType;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by leobui on 11/9/2017.
 */

public class UserDetailRequest extends BaseRequest{
    @Override
    protected String getPath() {
        return "test/user_0.json";
    }

    @Override
    protected Type getDataType() {
        Type type = new TypeToken<User>() {}.getType();
        return type;
    }



    @Override
    public RequestType getType() {
        return RequestType.GET;
    }


    @Override
    public Observable getApi() {
        return super.getApi();
    }
}
