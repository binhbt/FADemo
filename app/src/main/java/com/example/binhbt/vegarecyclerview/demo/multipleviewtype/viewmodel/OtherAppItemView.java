package com.example.binhbt.vegarecyclerview.demo.multipleviewtype.viewmodel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.VersionApp;
import com.squareup.picasso.Picasso;
import com.vn.vega.adapter.multipleviewtype.BinderViewHolder;
import com.vn.vega.adapter.multipleviewtype.VegaDataBinder;
import com.vn.vega.base.holder.VegaViewHolder;

import butterknife.Bind;

/**
 * Created by binhbt on 3/7/2017.
 */
public class OtherAppItemView  extends VegaDataBinder<VersionApp> {
    public OtherAppItemView(VersionApp data) {
        super(data);
    }

    int index = 0;
    public PhotoViewHolder holder1;

    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        holder1 = (PhotoViewHolder) holder;
        if (data.getResId() ==null) {
            Picasso.with(holder1.mImageView.getContext())
                    .load(data.getThumb())
                    .into(holder1.mImageView);
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
        ImageView mImageView;
        @Bind(R.id.tittle)
        TextView title;
        @Bind(R.id.item)
        public View item;

        public PhotoViewHolder(View view) {
            super(view);
        }
    }
}
