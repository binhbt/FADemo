package com.example.binhbt.demo.ui.imageloader;

import android.os.Bundle;
import android.widget.Toast;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.ui.activity.BaseActivity;
import com.fa.loader.widget.FAImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by leobui on 1/4/2018.
 */

public class FaImageLoaderDemoActivity extends BaseActivity{
    @Bind(R.id.img_thumb)FAImageView imgThumb;
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_faimage_loader;
    }
    @OnClick(R.id.btn_load)
    public void loadImage(){
        imgThumb
                .callback(new FAImageView.Callback() {
                    @Override
                    public void onStart() {
                        //Toast.makeText(holder1.mImageView.getContext(), "Start", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess() {
                        //Toast.makeText(holder1.mImageView.getContext(), "Success", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError() {
                        Toast.makeText(FaImageLoaderDemoActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                })
                .placeholder(R.drawable.common_full_open_on_phone)
                .error(R.drawable.ic_android_black_48dp)
                .circle(true)
                .cornerRadius(50f)
                .borderColor(android.R.color.transparent)
                .border(3)
                .loadImage("http://cdn2-www.craveonline.com/assets/uploads/2017/05/1_funny_photos_5_15_17.png");
    }
}
