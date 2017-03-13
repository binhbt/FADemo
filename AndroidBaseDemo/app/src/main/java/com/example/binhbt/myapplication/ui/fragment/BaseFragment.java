package com.example.binhbt.myapplication.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.binhbt.myapplication.net.EndPoints;
import com.example.binhbt.myapplication.ui.DemoApplication;
import com.vn.vega.base.ui.VegaFragment;
import com.vn.vega.ui.RxFragment;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by binhbt on 6/8/2016.
 */
public class BaseFragment extends VegaFragment {
    protected EndPoints api = DemoApplication.getApi();
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //Do nothing
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
