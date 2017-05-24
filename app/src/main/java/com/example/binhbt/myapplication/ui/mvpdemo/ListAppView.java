package com.example.binhbt.myapplication.ui.mvpdemo;

import com.example.binhbt.myapplication.model.VersionApp;
import com.vn.vega.base.mvp.MvpView;

import java.util.List;

/**
 * Created by binhbt on 3/7/2017.
 */
public interface ListAppView extends MvpView{
    void loadDataToView(List<VersionApp> result);

    void showError();

}
