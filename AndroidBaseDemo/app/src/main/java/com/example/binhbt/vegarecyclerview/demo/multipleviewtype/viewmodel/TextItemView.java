package com.example.binhbt.vegarecyclerview.demo.multipleviewtype.viewmodel;

import android.view.View;
import android.widget.TextView;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.vegarecyclerview.demo.multipleviewtype.model.TextItem;
import com.vn.vega.adapter.multipleviewtype.BinderViewHolder;
import com.vn.vega.adapter.multipleviewtype.VegaDataBinder;
import com.vn.vega.base.holder.VegaViewHolder;

import butterknife.Bind;

/**
 * Created by binhbt on 7/22/2016.
 */
public class TextItemView extends VegaDataBinder<TextItem> {
    public TextItemView(TextItem data){
        super(data);
    }
    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        TextViewHolder holder1 = (TextViewHolder)holder;
        holder1.mTxt.setText(data.getText());
    }

    @Override
    public BinderViewHolder newHolder(View parent) {
        return new TextViewHolder(parent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_text;
    }

    static class TextViewHolder extends VegaViewHolder {
        @Bind(R.id.text)
        TextView mTxt;
        public TextViewHolder(View view) {
            super(view);
        }
    }
}
