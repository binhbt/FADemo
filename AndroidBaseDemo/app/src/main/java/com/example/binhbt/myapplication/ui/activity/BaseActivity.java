package com.example.binhbt.myapplication.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.binhbt.myapplication.net.EndPoints;
import com.example.binhbt.myapplication.ui.DemoApplication;
import com.example.binhbt.myapplication.ui.mvpdemo.ListAppPresenter;
import com.vn.vega.base.mvp.BasePresenter;
import com.vn.vega.base.mvp.MvpView;
import com.vn.vega.base.ui.VegaActivity;
import com.vn.vega.net.RequestLoader;
import com.vn.vega.ui.RxActivity;

/**
 * Created by binhbt on 6/8/2016.
 */
public class  BaseActivity extends VegaActivity implements MvpView{
    protected BasePresenter presenter;
    protected EndPoints api = DemoApplication.getApi();
    public void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //Do nothing
        if (presenter != null)
        presenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        //Have no layout
        return 0;
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
        presenter.attachView(this);
        super.onDestroy();
    }
}
