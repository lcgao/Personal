package com.lcgao.personal.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.personal.adapter.CommonAdapter;
import com.lcgao.personal.adapter.ViewHolder;
import com.lcgao.personal.R;
import com.lcgao.personal.home.zhihu.ResultZhihu;
import com.lcgao.personal.home.zhihu.Storie;
import com.lcgao.personal.home.zhihu.TopStorie;
import com.lcgao.personal.home.zhihu.Zhihu;
import com.lcgao.personal.home.zhihu.ZhihuService;
import com.lcgao.personal.util.LogUtil;
import com.lcgao.personal.util.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
 * Created by lcgao on 2017/12/27.
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.rv_fragment_home_main)
    RecyclerView rvZhihu;
    @BindView(R.id.srl_fragment_home_refresh)
    SwipeRefreshLayout srlRefresh;
    private CommonAdapter<Zhihu> mAdapter;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private ZhihuService service = retrofit.create(ZhihuService.class);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvZhihu.setLayoutManager(layoutManager);
        rvZhihu.setHasFixedSize(true);
        rvZhihu.getItemAnimator().setChangeDuration(0);
        mAdapter = new CommonAdapter<Zhihu>(getActivity(), R.layout.item_news, new ArrayList<Zhihu>()) {
            @Override
            public void convert(ViewHolder holder, Zhihu zhihu) {
                holder.setText(R.id.tv_title_item_news, zhihu.getTitle());
                if(!TextUtils.isEmpty(zhihu.getImage())){
                    holder.setVisibility(R.id.iv_image_item_news, View.VISIBLE);
                    Picasso.with(getActivity())
                            .load(zhihu.getImage())
                            .placeholder(R.drawable.ic_default_img)
                            .into((ImageView)holder.getView(R.id.iv_image_item_news));
                }else {
                    holder.setVisibility(R.id.iv_image_item_news, View.GONE);
                }
            }
        };
        rvZhihu.setAdapter(mAdapter);
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

    private void getNews(){
        service.getLatestZhihu()
                .clone()
                .enqueue(new Callback<ResultZhihu>() {
                    @Override
                    public void onResponse(Call<ResultZhihu> call, Response<ResultZhihu> response) {
                        srlRefresh.setRefreshing(false);
                        ResultZhihu zhihu = response.body();
                        if(zhihu == null){
                            ToastUtil.s(response.body() + "为空");
                            LogUtil.l(response.body() + "为空");
                        }else {
                            LogUtil.l(zhihu.toString());
                            List<TopStorie> topStories = zhihu.getTop_stories();
                            List<Storie> stories = zhihu.getStories();
                            List<Zhihu> zhihus = new ArrayList<>();
                            Map<Integer, Zhihu> map = new HashMap<>();
                            if(topStories!=null){
                                for(TopStorie ts : topStories){
                                    map.put(ts.getId(), ts);
                                }
                            }
                            if(stories != null){
                                for(Storie s : stories){
                                    map.put(s.getId(), s);
                                }
                            }
                            zhihus.addAll(map.values());
                            ToastUtil.s("更新" + zhihus.size() +"条");
                            mAdapter.replaceData(zhihus);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultZhihu> call, Throwable t) {
                        srlRefresh.setRefreshing(false);
                        LogUtil.l("请求失败，" + t.getMessage());
                    }
                });
    }
}
