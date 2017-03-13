package com.example.binhbt.myapplication.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.ui.fragment.UserListFragment;


/**
 * Created by binhbt on 6/8/2016.
 */
public class UserListActivity extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new UserListFragment());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout;
    }
}
