package com.lcgao.personal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lcgao on 2017/12/28.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder>{
    private List<T> mData;
    private Context mContext;
    private LayoutInflater mInflate;
    private int mLayoutId;

    public CommonAdapter(Context context, int layoutId, List<T> data){
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mData = data;
        this.mInflate = LayoutInflater.from(context);
    }

    public void setData(List<T> data){
        this.mData = data;
    }

    public void replaceData(List<T> data){
        setData(data);
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if(data == null || data.size() == 0){
            return;
        }
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.get(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract void convert(ViewHolder holder, T t);
}
