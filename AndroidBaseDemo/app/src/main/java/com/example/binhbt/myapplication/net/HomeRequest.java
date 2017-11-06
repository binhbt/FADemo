package com.example.binhbt.myapplication.net;

import com.example.binhbt.myapplication.model.VersionApp;
import com.google.gson.reflect.TypeToken;
import com.vn.vega.base.net.request.RequestType;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by leobui on 10/27/2017.
 */

public class HomeRequest extends BaseRequest {
    @Override
    protected String getPath() {
        return "other_app.json";
    }

    @Override
    protected Type getDataType() {
        Type type = new TypeToken<List<VersionApp>>() {}.getType();
        return type;
        //return getSuperclassTypeParameter(getClass());
    }

    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

}
