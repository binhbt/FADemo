package com.example.binhbt.myapplication.viewmodel;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.VersionApp;
import com.fa.loader.widget.FAImageView;
import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.base.adapter.FaAdapter;
import com.vn.fa.base.holder.VegaBinderView;
import com.vn.fa.base.holder.VegaViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Created by binhbt on 3/7/2017.
 */
public class OtherAppItemView  extends VegaBinderView<VersionApp> {
    public OtherAppItemView(VersionApp data) {
        super(data);
    }


    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
//        if (!(holder instanceof PhotoViewHolder)) return;
        PhotoViewHolder holder1 = (PhotoViewHolder) holder;
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
                        .circle(true)
                        .cornerRadius(50f)
                        .borderColor(R.color.colorAccent)
                        .border(3)
                        .loadImage(data.getThumb());
        }else{
            holder1.mImageView.setImageDrawable(data.getResId());
        }
        holder1.title.setText(data.getTitle());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                List<VegaBinderView>  binderList= (List<VegaBinderView>)(List)((FaAdapter)getAdapter()).getBinderList();
//                VersionApp versionApp = (VersionApp)binderList.get(1).getData();
            }
        });
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
