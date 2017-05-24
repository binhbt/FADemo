package com.vn.vega.adapter.multipleviewtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by binhbt on 7/21/2016.
 */
abstract public class VegaDataBinder<T> extends DataBinder<BinderViewHolder> implements IViewBinder{
    protected T data;
    protected DataBindAdapter mDataBindAdapter;
    public VegaDataBinder(T data){
        this.data = data;
    }
    @Override
    public BinderViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutResource(), parent, false);
        return newHolder(view);
    }
    abstract public int getLayoutResource();
    abstract public BinderViewHolder newHolder(View parent);
    @Override
    public int getItemCount() {
        return 1;
    }
    public T getData(){
        return data;
    }
    public VegaDataBinder data(T data){
        this.data = data;
        return this;
    }
    public void setData(T data){
        this.data = data;
    }
    public VegaDataBinder adapter(DataBindAdapter dataBindAdapter){
        this.mDataBindAdapter = dataBindAdapter;
        return this;
    }
    public DataBindAdapter getAdapter(){
        return mDataBindAdapter;
    }
    public DataBinder getViewBinder(){
        return this;
    }


}
