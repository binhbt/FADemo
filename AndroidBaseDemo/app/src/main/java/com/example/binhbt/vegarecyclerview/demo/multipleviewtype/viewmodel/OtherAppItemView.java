package com.example.binhbt.vegarecyclerview.demo.multipleviewtype.viewmodel;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.VersionApp;
import com.fa.loader.widget.FAImageView;
import com.vn.vega.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.base.holder.VegaBinderView;
import com.vn.fa.base.holder.VegaViewHolder;

import butterknife.Bind;

/**
 * Created by binhbt on 3/7/2017.
 */
public class OtherAppItemView  extends VegaBinderView<VersionApp> {
    public OtherAppItemView(VersionApp data) {
        super(data);
    }

    int index = 0;
    public PhotoViewHolder holder1;

    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        holder1 = (PhotoViewHolder) holder;
        if (data.getResId() ==null) {
                holder1.mImageView
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
                                Toast.makeText(holder1.mImageView.getContext(), "Error", Toast.LENGTH_LONG).show();

                            }
                        })
                        .placeholder(R.drawable.common_full_open_on_phone)
                        .error(R.drawable.ic_android_black_48dp)
                        .loadImage(data.getThumb());
        }else{
            holder1.mImageView.setImageDrawable(data.getResId());
        }
        holder1.title.setText(data.getTitle());

    }

    private boolean isCreate = true;

    @Override
    public BinderViewHolder newHolder(View parent) {
        return new PhotoViewHolder(parent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_other_app;
    }

    public class PhotoViewHolder extends VegaViewHolder {
        @Bind(R.id.thumb)
        FAImageView mImageView;
        @Bind(R.id.tittle)
        TextView title;
        @Bind(R.id.item)
        public View item;

        public PhotoViewHolder(View view) {
            super(view);
        }
    }
}
