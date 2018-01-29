package com.lcgao.personal.home.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.personal.R;
import com.lcgao.personal.WebActivity;
import com.lcgao.personal.adapter.CommonAdapter;
import com.lcgao.personal.adapter.ViewHolder;
import com.lcgao.personal.util.LogUtil;
import com.lcgao.personal.util.ToastUtil;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lcgao on 2018/1/2.
 */

public class ZhihuFragment extends Fragment {
    @BindView(R.id.rv_fragment_main)
    RecyclerView rvZhihu;
    @BindView(R.id.srl_fragment_main_refresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.ll_nothing)
    LinearLayout llNothing;
    private CommonAdapter<Zhihu> mAdapter;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private ZhihuService service = retrofit.create(ZhihuService.class);

    private FragmentManager fragmentManager;

    private List<Zhihu> zhihus = new ArrayList<>();
    private Map<Long, Zhihu> map = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        ButterKnife.bind(this, view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvZhihu.setLayoutManager(layoutManager);
        rvZhihu.setHasFixedSize(true);
        rvZhihu.getItemAnimator().setChangeDuration(0);
        mAdapter = new CommonAdapter<Zhihu>(getActivity(), R.layout.item_news, new ArrayList<Zhihu>()) {
            @Override
            public void convert(ViewHolder holder, final Zhihu zhihu) {
                holder.setText(R.id.tv_title_item_news, zhihu.getTitle());
                if (!TextUtils.isEmpty(zhihu.getImage())) {
                    holder.setVisibility(R.id.iv_image_item_news, View.VISIBLE);
                    Picasso.with(getActivity())
                            .load(zhihu.getImage())
                            .placeholder(R.drawable.ic_default_img)
                            .into((ImageView) holder.getView(R.id.iv_image_item_news));
                } else {
                    holder.setVisibility(R.id.iv_image_item_news, View.GONE);
                }
                holder.setOnClickListener(R.id.ll_item_news, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.l("id：" + zhihu.getId());
//                        FragmentTransaction ft = fragmentManager.beginTransaction();
//                        WebViewFragment webViewFragment = new WebViewFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", zhihu.getTitle());
                        bundle.putString("id", zhihu.getId() + "");
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
//                        webViewFragment.setArguments(bundle);
//                        ft.hide(ZhihuFragment.this);
//                        ft.add(R.id.content, webViewFragment);
//                        ft.addToBackStack("webview");
//                        ft.commit();
                    }
                });
            }
        };
        rvZhihu.setAdapter(mAdapter);
        rvZhihu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int visibleItemCount = recyclerView.getChildCount();

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == totalItemCount - 1
                        && visibleItemCount > 0) {
                    //加载更多
//                    ToastUtil.s("滑到底部啦..");
                    loadBefore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        srlRefresh.setRefreshing(true);
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.themecolor));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
            }
        });
        getNews();
        return view;
    }

    private int count;
    long time = new Date().getTime();
    private void loadBefore() {
        final long time_new = time - 1000 * 60 * 60 * 24 * count ++;
        String date = new SimpleDateFormat("yyyyMMdd").format(time_new);
        service.getBefore(date)
                .clone()
                .enqueue(new Callback<ResultZhihu>() {
                    @Override
                    public void onResponse(Call<ResultZhihu> call, Response<ResultZhihu> response) {

                        ResultZhihu zhihu = response.body();
                        if(zhihu == null){
                            ToastUtil.s(response.body() + "为空");
                            LogUtil.l(response.body() + "为空");
                            return;
                        }
//                        ToastUtil.s(new SimpleDateFormat("yyyy年M月d日").format(time_new));
                        LogUtil.d("日期：" + new SimpleDateFormat("yyyy年M月d日").format(time_new));
                        List<Storie> stories = zhihu.getStories();
//                        if (stories != null) {
//                            for (Storie s : stories) {
//                                map.put(s.getId(), s);
//                            }
//                        }
                        zhihus.addAll(stories);
                        mAdapter.replaceData(zhihus);
                    }

                    @Override
                    public void onFailure(Call<ResultZhihu> call, Throwable t) {

                    }
                });
    }

    private void getNews() {
        service.getLatestZhihu()
                .clone()
                .enqueue(new Callback<ResultZhihu>() {
                    @Override
                    public void onResponse(Call<ResultZhihu> call, Response<ResultZhihu> response) {
                        LogUtil.d(call.request());
                        LogUtil.d(call.request().headers());
                        srlRefresh.setRefreshing(false);
                        ResultZhihu zhihu = response.body();
                        if (zhihu == null) {
                            ToastUtil.s(response.body() + "为空");
                            LogUtil.l(response.body() + "为空");
                        } else {
//                            LogUtil.l(zhihu.toString());
                            List<TopStorie> topStories = zhihu.getTop_stories();
                            List<Storie> stories = zhihu.getStories();
                            if (topStories != null) {
                                for (TopStorie ts : topStories) {
                                    map.put(ts.getId(), ts);
                                }
                            }
                            if (stories != null) {
                                for (Storie s : stories) {
                                    map.put(s.getId(), s);
                                }
                            }
                            zhihus.addAll(map.values());
                            ToastUtil.s("更新" + zhihus.size() + "条");
//                            mAdapter.replaceData(zhihus);
                            setData(zhihus);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultZhihu> call, Throwable t) {
                        srlRefresh.setRefreshing(false);
                        LogUtil.l("请求失败，" + t.getMessage());
                    }
                });
    }

    public void setData(List<Zhihu> zhihus){
        if(zhihus == null || zhihus.size() == 0){
            llNothing.setVisibility(View.VISIBLE);
            return;
        }
        llNothing.setVisibility(View.GONE);
        mAdapter.replaceData(zhihus);
    }


}
