package com.example.binhbt.vegarecyclerview.demo.multipleviewtype.model;

import com.example.binhbt.vegarecyclerview.demo.multipleviewtype.viewmodel.PhotoItemView;
import com.vn.vega.adapter.multipleviewtype.DataBinder;
import com.vn.vega.adapter.multipleviewtype.IViewBinder;

/**
 * Created by binhbt on 7/22/2016.
 */
public class PhotoItem implements IViewBinder{
    private String url = "http://i.funny.pho.to/templates/red_boost/preview220.jpg";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public DataBinder getViewBinder() {
        return new PhotoItemView(this);
    }
}
