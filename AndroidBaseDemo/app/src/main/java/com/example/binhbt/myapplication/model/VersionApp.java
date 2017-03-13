package com.example.binhbt.myapplication.model;

import android.graphics.drawable.Drawable;

import com.example.binhbt.vegarecyclerview.demo.multipleviewtype.viewmodel.OtherAppItemView;
import com.vn.vega.adapter.multipleviewtype.DataBinder;
import com.vn.vega.adapter.multipleviewtype.IViewBinder;

/**
 * Created by binhbt on 3/7/2017.
 */
public class  VersionApp implements IViewBinder {
    private String id;
    private String type;
    private String title;
    private String pakage;
    private String url;
    private String versionName;
    private String versionCode;
    private String thumb;
    private transient Drawable resId;

    public Drawable getResId() {
        return resId;
    }

    public void setResId(Drawable resId) {
        this.resId = resId;
    }

    @Override
    public DataBinder getViewBinder() {
        return new OtherAppItemView(this);
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPakage() {
        return pakage;
    }

    public void setPakage(String pakage) {
        this.pakage = pakage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
