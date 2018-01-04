package com.example.binhbt.farecyclerview.multipleviewtype.viewmodel;

import android.view.View;
import android.widget.TextView;

import com.example.binhbt.demo.R;
import com.example.binhbt.farecyclerview.multipleviewtype.model.TextItem;
import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.adapter.multipleviewtype.VegaDataBinder;
import com.vn.fa.base.holder.VegaViewHolder;

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
