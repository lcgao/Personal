package com.lcgao.music_module.recommend.model;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lcgao.music_module.R;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lcgao on 2018/1/11.
 */

public class RecommendFragment extends Fragment {
    @BindView(R.id.banner)
    MZBannerView<String> mMZBannerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        List<String> list = new ArrayList<>();
        list.add("http://p1.music.126.net/4e1C1XMb4peZg7aP1pQ88Q==/109951163831078534.jpg");
        list.add("http://p1.music.126.net/u2GdpTZN9kjEIZ4fo9wbwQ==/109951163831169841.jpg");
        list.add("http://p1.music.126.net/DI0aw3v7fCmO1LCHm1Yelg==/109951163831073451.jpg");
        list.add("http://p1.music.126.net/UQV3u4aCg822dhJFVOJcWg==/109951163827220752.jpg");
        mMZBannerView.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    public class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
//            mImageView.setImageResource(data);
            Picasso.with(getActivity().getApplicationContext())
                    .load(data)
                    .placeholder(R.drawable.ic_default_img)
                    .error(R.drawable.ic_default_img)
                    .into(mImageView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBannerView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBannerView.start();
    }
}
