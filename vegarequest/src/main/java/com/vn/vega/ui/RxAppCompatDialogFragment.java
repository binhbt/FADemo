package com.vn.vega.ui;

import android.support.v7.app.AppCompatDialogFragment;

import com.vn.vega.net.RequestLoader;

/**
 * Created by binhbt on 6/22/2016.
 */
public abstract class RxAppCompatDialogFragment extends AppCompatDialogFragment {
    @Override
    public void onDestroyView() {
        RequestLoader.getDefault().cancelAll(this);
        super.onDestroyView();
    }
}
