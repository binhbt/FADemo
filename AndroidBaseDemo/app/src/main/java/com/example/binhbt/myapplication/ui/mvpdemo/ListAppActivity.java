package com.example.binhbt.myapplication.ui.mvpdemo;

import android.os.Bundle;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.VersionApp;
import com.example.binhbt.myapplication.ui.activity.BaseActivity;
import com.vn.vega.adapter.multipleviewtype.IViewBinder;
import com.vn.vega.adapter.multipleviewtype.VegaBindAdapter;
import com.vn.vega.widget.RecyclerViewWrapper;

import java.util.List;

import butterknife.Bind;

/**
 * Created by binhbt on 3/7/2017.
 */
public class ListAppActivity extends BaseActivity implements ListAppView{
    @Bind(R.id.list)
    RecyclerViewWrapper mRecycler;
    private VegaBindAdapter mAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        mAdapter = new VegaBindAdapter();

        presenter = new ListAppPresenter();
        presenter.attachView(this);
        ((ListAppPresenter)presenter).loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_app;
    }

    @Override
    public void loadDataToView(List<VersionApp> result) {
        List<IViewBinder> viewBinders = (List<IViewBinder>) (List) result;
        mAdapter.addAllDataObject(viewBinders, true);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showError() {
        showToastMessage("Got Error");
    }
}
