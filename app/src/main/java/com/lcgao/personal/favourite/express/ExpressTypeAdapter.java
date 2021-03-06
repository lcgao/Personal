package com.lcgao.personal.favourite.express;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lcgao.personal.R;

import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * Created by lcgao on 2018/1/24.
 */

public class ExpressTypeAdapter extends IndexableAdapter<ExpressType> {
    private LayoutInflater mInflater;

    public ExpressTypeAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_index, parent, false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_text_only, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        IndexVH vh = (IndexVH) holder;
        vh.textView.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, ExpressType entity) {
        ContentVH vh = (ContentVH) holder;
        vh.textView.setText(entity.getExpressName());
    }

    private class IndexVH extends RecyclerView.ViewHolder{
        TextView textView;
        public IndexVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder{
        TextView textView;

        public ContentVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_text_only_title);
            itemView.findViewById(R.id.tv_item_text_only_content).setVisibility(View.GONE);
        }
    }

}
