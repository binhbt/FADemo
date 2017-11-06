package com.example.binhbt.myapplication.net;

import com.vn.vega.base.net.FARequest;

/**
 * Created by leobui on 10/30/2017.
 */

public class DemoRequestFactory extends VegaRequestFactory<DemoRequestFactory.DemoRequestType> {
    static volatile DemoRequestFactory vegaRequestFactory;

    public static VegaRequestFactory getInstance() {
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
        HOME_REQUEST("other_app.json"),
        DETAIL_REQUEST("other_app1.json");
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

    @Override
    public FARequest getRequest(DemoRequestType demoRequestType) {
        return new BaseRequest().path(demoRequestType.toString());
    }
}
