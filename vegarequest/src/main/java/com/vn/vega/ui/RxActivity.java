package com.vn.vega.ui;

import android.support.v7.app.AppCompatActivity;

import com.vn.vega.net.R;
import com.vn.vega.net.RequestLoader;

/**
 * Created by binhbt on 6/22/2016.
 */
public abstract class RxActivity extends AppCompatActivity {
    protected void onDestroy() {
        RequestLoader.getDefault().cancelAll(this);
        super.onDestroy();
    }
}
