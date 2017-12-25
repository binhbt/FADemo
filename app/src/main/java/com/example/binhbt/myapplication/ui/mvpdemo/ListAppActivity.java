package com.example.binhbt.myapplication.ui.mvpdemo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.VersionApp;
import com.example.binhbt.myapplication.ui.activity.BaseActivity;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;
import com.vn.fa.base.adapter.FaAdapter;
import com.vn.fa.base.holder.OnItemClickListener;
import com.vn.fa.base.util.FaLog;
import com.vn.fa.widget.RecyclerViewWrapper;

import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

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
                FaLog.e("aa "+pos);
            }
        });
        presenter = new ListAppPresenter();
        presenter.attachView(this);
        ((ListAppPresenter)presenter).loadData();
        FaLog.e("abc", "FaLog test");
        //Timber.d("Hello %s %s!", "ABC", "abc");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_app;
    }

    @Override
    public void loadDataToView(List<VersionApp> result) {
        VersionApp versionApp1 = result.get(20);
        for (int i=0; i< result.size(); i++){
            if (i%2 ==0){
                result.get(i).setViewType(VersionApp.ViewType.CIRCLE);
            }else{
                result.get(i).setViewType(VersionApp.ViewType.ROUND);
            }
        }
        List<IViewBinder> viewBinders = (List<IViewBinder>) (List) result;
        mAdapter.addAllDataObject(viewBinders, true);
        mRecycler.setAdapter(mAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //VersionApp versionApp = versionApp1;
                //mAdapter.insertDataObject(versionApp, 3);
                //mAdapter.insertDataObject(versionApp, 0);
                //mAdapter.notifyItemChanged(0);

            }
        }, 5000);
    }

    @Override
    public void showError() {
        showToastMessage("Got Error");
    }
}
