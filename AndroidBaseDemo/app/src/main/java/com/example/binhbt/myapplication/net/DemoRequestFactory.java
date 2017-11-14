package com.example.binhbt.myapplication.net;

import com.vn.fa.base.VegaRequestFactory;
import com.vn.fa.base.net.FaRequest;

/**
 * Created by leobui on 10/30/2017.
 */

public class DemoRequestFactory extends VegaRequestFactory {
    static volatile DemoRequestFactory vegaRequestFactory;

    public static DemoRequestFactory getInstance() {
        if (vegaRequestFactory == null) {
            synchronized (VegaRequestFactory.class) {
                if (vegaRequestFactory == null) {
                    vegaRequestFactory = new DemoRequestFactory();
                }
            }
        }
        return vegaRequestFactory;
    }

    public enum DemoRequestType {
        HOME_REQUEST("com.example.binhbt.myapplication.net.HomeRequest"),
        HOME_REQUEST1("other_app1.json");
        private String text;

        private DemoRequestType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    @Override
    public void init() {

    }
    public FaRequest getRequest(DemoRequestType demoRequestType) {
        return getRequest(demoRequestType.toString());
        //return new BaseRequest().path(demoRequestType.toString());
    }
}
