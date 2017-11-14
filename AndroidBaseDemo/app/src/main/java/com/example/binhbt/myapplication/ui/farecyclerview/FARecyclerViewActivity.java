package com.example.binhbt.myapplication.ui.farecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.net.FATestRecyclerViewRequest;
import com.vn.fa.base.holder.OnItemClickListener;
import com.vn.fa.base.ui.FaActivity;
import com.vn.fa.base.widget.FARecyclerview;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by leobui on 11/3/2017.
 */
public class FARecyclerViewActivity extends FaActivity {
    @Bind(R.id.list)FARecyclerview recyclerview;
    @OnClick(R.id.list)
    public void clickList(){

    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerview
                .api(new FATestRecyclerViewRequest())
                //.api(DemoRequestFactory.getInstance().getRequest())
                .canRefresh(true)
                .canLoadMore(true)
                .container(this)
                .load();
        recyclerview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(FARecyclerViewActivity.this, "Click to Item "+ pos, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fa_recyclerview;
    }
}


