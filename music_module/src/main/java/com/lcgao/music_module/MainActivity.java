package com.lcgao.music_module;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.common_library.util.RouterUtil;
import com.squareup.picasso.Picasso;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterUtil.MODULE_MUSIC_MAIN_ACTIVITY_URL)
public class MainActivity extends BaseActivity {
//    @BindView(R.id.banner_guide_content)
//    BGABanner mBanner;

    @BindView(R.id.banner)
    MZBannerView<String> mMZBannerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(MainActivity.this);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        toolbar.setTitle("音乐");
        setSupportActionBar(toolbar);
//        mBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
//            @Override
//            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
////                Glide.with(MainActivity.this)
////                        .load(model)
////                        .placeholder(R.drawable.holder)
////                        .error(R.drawable.holder)
////                        .centerCrop()
////                        .dontAnimate()
////                        .into(itemView);
//                Picasso.with(MainActivity.this)
//                        .load(model)
//                        .placeholder(R.drawable.ic_default_img)
//                        .error(R.drawable.ic_default_img)
//                        .into(itemView);
//            }
//        });
//        mBanner.setData(Arrays.asList(
//                "http://p1.music.126.net/4e1C1XMb4peZg7aP1pQ88Q==/109951163831078534.jpg",
//                "http://p1.music.126.net/u2GdpTZN9kjEIZ4fo9wbwQ==/109951163831169841.jpg",
//                "http://p1.music.126.net/DI0aw3v7fCmO1LCHm1Yelg==/109951163831073451.jpg",
//                "http://p1.music.126.net/UQV3u4aCg822dhJFVOJcWg==/109951163827220752.jpg"),
//                Arrays.asList("VIP专享", "VIP专享", "独家", "独家专访"));

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
            Picasso.with(MainActivity.this)
                    .load(data)
                    .placeholder(R.drawable.ic_default_img)
                    .error(R.drawable.ic_default_img)
                    .into(mImageView);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBannerView.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBannerView.start();//开始轮播
    }
}
