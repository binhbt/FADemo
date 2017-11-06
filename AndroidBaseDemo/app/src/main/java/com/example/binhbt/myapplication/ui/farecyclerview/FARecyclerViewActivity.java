package com.example.binhbt.myapplication.ui.farecyclerview;

import android.os.Bundle;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.net.FATestRecyclerViewRequest;
import com.vn.vega.base.ui.VegaActivity;
import com.vn.vega.base.widget.FARecyclerview;

import butterknife.Bind;

/**
 * Created by leobui on 11/3/2017.
 */
public class FARecyclerViewActivity extends VegaActivity{
    @Bind(R.id.list)FARecyclerview recyclerview;
    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerview.api(new FATestRecyclerViewRequest())
                .canRefresh(true)
                .canLoadMore(true)
                .container(this)
                .load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fa_recyclerview;
    }
}


