package com.lcgao.personal.home.essay;

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
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.personal.R;
import com.lcgao.personal.adapter.CommonAdapter;
import com.lcgao.personal.adapter.ViewHolder;
import com.lcgao.personal.util.LogUtil;
import com.lcgao.personal.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lcgao on 2018/1/11.
 */

public class EssayFragment extends Fragment {
    @BindView(R.id.rv_fragment_main)
    RecyclerView recyclerView;
    @BindView(R.id.srl_fragment_main_refresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.ll_nothing)
    LinearLayout llNothing;

    private int mCount = 10;
    private List<EssayEntity> mEssayEntities = new ArrayList<>();
    private CommonAdapter<EssayEntity> mAdapter;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://interface.meiriyiwen.com/article/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private EssayService mService = retrofit.create(EssayService.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.getItemAnimator().setChangeDuration(0);
        mAdapter = new CommonAdapter<EssayEntity>(getActivity(), R.layout.item_news, new ArrayList<EssayEntity>()){

            @Override
            public void convert(ViewHolder holder, EssayEntity essayEntity) {
                holder.setText(R.id.tv_title_item_news, essayEntity.getTitle() + " -- " +essayEntity.getAuthor());
                holder.setText(R.id.tv_content_item_news, essayEntity.getDigest());
                holder.setVisibility(R.id.iv_image_item_news, View.GONE);
            }
        };
        recyclerView.setAdapter(mAdapter);
//        getEssays(new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    private void getEssays(String date){
        if(TextUtils.isEmpty(date) || mCount == 0){
            if(mAdapter != null){
                setData(mEssayEntities);
            }
            return;
        }
        Map<String, String> map = new HashMap<>();
//        map.put("date", date);
        map.put("dev", "1");
        mService.getEssay(map)
                .clone()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        LogUtil.d(call.request());
                        LogUtil.d(response.toString());
                        ResponseBody data = response.body();
                        if(data == null){
                            ToastUtil.s("Essay response.body()为空");
                            LogUtil.l("Essay response.body()为空");
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
//        mService.getEssay(map)
//                .clone()
//                .enqueue(new Callback<EssayData>() {
//                    @Override
//                    public void onResponse(Call<EssayData> call, Response<EssayData> response) {
//                        LogUtil.d(response.toString());
//                        EssayData data = response.body();
//                        if(data == null){
//                            ToastUtil.s("Essay response.body()为空");
//                            LogUtil.l("Essay response.body()为空");
//                            return;
//                        }
//                        EssayEntity essayEntity = data.getData();
//                        if(essayEntity == null){
//                            ToastUtil.s("Essay data为空");
//                            LogUtil.l("Essay data为空");
//                            return;
//                        }
//                        mEssayEntities.add(essayEntity);
//                        mCount --;
//                        getEssays(essayEntity.getDate().getPrev());
//                    }
//
//                    @Override
//                    public void onFailure(Call<EssayData> call, Throwable t) {
//                        ToastUtil.s(t.getMessage());
//                        LogUtil.l("请求失败，" + t.getMessage());
//                    }
//                });
    }

    public void setData(List<EssayEntity> essayEntities){
        if(essayEntities == null || essayEntities.size() == 0){
            llNothing.setVisibility(View.VISIBLE);
            return;
        }
        llNothing.setVisibility(View.GONE);
        mAdapter.replaceData(essayEntities);
    }

    private List<String> getLastWeekDate(){
        List<String> week = new ArrayList<>();
        Date date = new Date();
        long now = date.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        long oneDay = 1000 * 60 * 60 * 24;
        for(int i = 0; i < 7; i ++){
            week.add(sdf.format(now));
            now -= oneDay;
        }
        return week;
    }
}
