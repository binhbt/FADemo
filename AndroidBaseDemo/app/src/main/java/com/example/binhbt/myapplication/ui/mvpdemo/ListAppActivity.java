package com.example.binhbt.myapplication.ui.mvpdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.VersionApp;
import com.example.binhbt.myapplication.ui.activity.BaseActivity;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;
import com.vn.fa.adapter.multipleviewtype.VegaBindAdapter;
import com.vn.fa.base.adapter.FaAdapter;
import com.vn.fa.base.holder.OnItemClickListener;
import com.vn.fa.widget.RecyclerViewWrapper;

import java.util.List;

import butterknife.Bind;

/**
 * Created by binhbt on 3/7/2017.
 */
public class ListAppActivity extends BaseActivity implements ListAppView{
    @Bind(R.id.list)
    RecyclerViewWrapper mRecycler;
    private FaAdapter mAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        mAdapter = new FaAdapter();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Log.e("aa", "aa "+pos);
            }
        });
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
