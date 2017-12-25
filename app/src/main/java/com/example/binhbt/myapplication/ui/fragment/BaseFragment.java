package com.example.binhbt.myapplication.ui.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.binhbt.myapplication.net.EndPoints;
import com.example.binhbt.myapplication.ui.DemoApplication;
import com.vn.fa.base.ui.FaFragment;


/**
 * Created by binhbt on 6/8/2016.
 */
public class BaseFragment extends FaFragment {
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
