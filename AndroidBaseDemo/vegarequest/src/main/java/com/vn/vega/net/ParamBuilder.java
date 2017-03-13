package com.vn.vega.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by binhbt on 6/22/2016.
 */
public class ParamBuilder {
    private Map<String, String> params = new HashMap<>();

    public ParamBuilder add(String key, String value){
        params.put(key, value);
        return this;
    }
    public Map<String, String> build(){
        return params;
    }

}
