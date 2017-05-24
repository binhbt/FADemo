package com.vn.vega.ui;

import android.support.v4.app.Fragment;

import com.vn.vega.net.RequestLoader;

/**
 * Created by binhbt on 6/22/2016.
 */
public class RxFragment extends Fragment{
    @Override
    public void onDestroyView() {
        RequestLoader.getDefault().cancelAll(this);
        super.onDestroyView();
    }
}
