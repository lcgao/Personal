package com.lcgao.personal.favourite.news;

import android.content.Intent;
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
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.common_library.util.NetworkUtil;
import com.lcgao.personal.MyApplication;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lcgao on 2018/1/11.
 */

public class NewsFragment extends Fragment {
    private String mCategory;

    //    @BindView(R.id.tv_nothing_tip)
//    TextView tv_content;
    @BindView(R.id.rv_fragment_main)
    RecyclerView recyclerView;
    @BindView(R.id.ll_nothing)
    LinearLayout llNothing;
    @BindView(R.id.srl_fragment_main_refresh)
    SwipeRefreshLayout srlRefresh;
    CommonAdapter<NewsContent> mAdapter;
    private Retrofit retrofit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategory = bundle.getString("category");
//            ToastUtil.s(mCategory);
        }
        initView();
        return view;
    }

    private void initView() {
        String category;
        switch (mCategory) {
            case "热点":
                category = "news_hot";
                break;
            case "视频":
                category = "video";
                break;
            case "社会":
                category = "news_society";
                break;
            case "娱乐":
                category = "news_entertainment";
                break;
            case "问答":
                category = "question_and_answer";
                break;
            case "图片":
                category = "组图";
                break;
            case "科技":
                category = "news_tech";
                break;
            case "汽车":
                category = "news_car";
                break;
            case "体育":
                category = "news_sports";
                break;
            case "财经":
                category = "news_finance";
                break;

            case "军事":
                category = "news_military";
                break;
            case "国际":
                category = "news_world";
                break;
            case "段子":
                category = "essay_joke";
                break;
            case "趣图":
                category = "image_funny";
                break;
            default:
                category = "news_hot";
                break;
        }
        String url = "http://is.snssdk.com/api/news/feed/v51/?category=" + category + "&refer=1&count=20&min_behot_time=1491981025&last_refresh_sub_entrance_interval=1491981165&loc_mode=&loc_time=1491981000&latitude=&longitude=&city=&tt_from=pull&lac=&cid=&cp=&iid=0123456789&device_id=12345678952&ac=wifi&channel=&aid=&app_name=&version_code=&version_name=&device_platform=&ab_version=&ab_client=&ab_group=&ab_feature=&abflag=3&ssmix=a&device_type=&device_brand=&language=zh&os_api=&os_version=&openudid=1b8d5bf69dc4a561&manifest_version_code=&resolution=&dpi=&update_version_code=&_rticket=";
        retrofit = NetworkUtil.buildRetrofit(MyApplication.getInstance(), null, url);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.getItemAnimator().setChangeDuration(0);
        mAdapter = new CommonAdapter<NewsContent>(getActivity(), R.layout.item_news, new ArrayList<NewsContent>()) {
            @Override
            public void convert(ViewHolder holder, final NewsContent newsContent) {
                holder.setText(R.id.tv_title_item_news, newsContent.getTitle());
                holder.setText(R.id.tv_content_item_news, newsContent.get_abstract());
                holder.setVisibility(R.id.layout_detail, View.VISIBLE);
                holder.setVisibility(R.id.layout_item_news_time, View.GONE);
//                String time = new SimpleDateFormat("yyyy/M/d HH:mm").format(newsContent.getPublish_time());
//                holder.setText(R.id.tv_item_news_time, time);
                String keyWords = newsContent.getKeywords();
                if (!TextUtils.isEmpty(keyWords)) {
                    String[] keys = keyWords.split(",");
                    if (!TextUtils.isEmpty(keyWords) && keys.length > 0) {
                        holder.setText(R.id.tv_item_news_tag, keys[0]);
                    } else {
                        holder.setVisibility(R.id.tv_item_news_tag, View.GONE);
                    }
                } else {
                    holder.setVisibility(R.id.tv_item_news_tag, View.GONE);
                }
                holder.setText(R.id.tv_item_news_read_count, newsContent.getRead_count());
                holder.setText(R.id.tv_item_news_from, newsContent.getSource());
                List<NewsImages> image = newsContent.getImage_list();
                if (image != null && image.size() > 0 && image.get(0) != null) {
                    if (!TextUtils.isEmpty(image.get(0).getUrl())) {
                        holder.setVisibility(R.id.iv_image_item_news, View.VISIBLE);
                        Picasso.with(getActivity())
                                .load(image.get(0).getUrl())
                                .placeholder(R.drawable.ic_default_img)
                                .into((ImageView) holder.getView(R.id.iv_image_item_news));
                    } else {
                        holder.setVisibility(R.id.iv_image_item_news, View.GONE);
                    }
                } else {
                    holder.setVisibility(R.id.iv_image_item_news, View.GONE);
                }

                holder.setOnClickListener(R.id.ll_item_news, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        LogUtil.l("id：" + newsContent.getId());
//                        FragmentTransaction ft = fragmentManager.beginTransaction();
//                        WebViewFragment webViewFragment = new WebViewFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", newsContent.getTitle());
                        bundle.putString("url", newsContent.getArticle_url());
                        bundle.putString("content", newsContent.get_abstract());
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
        recyclerView.setAdapter(mAdapter);
        srlRefresh.setRefreshing(true);
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.themecolor));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
            }
        });
        getNews();
    }

    private void getNews() {
        retrofit.create(NewsService.class)
                .getNews("")
                .clone()
                .enqueue(new Callback<ResultNews>() {
                    @Override
                    public void onResponse(Call<ResultNews> call, Response<ResultNews> response) {
                        srlRefresh.setRefreshing(false);
                        ResultNews resultNews = response.body();
                        if (resultNews == null) {
                            ToastUtil.s("response.body()为空");
                            LogUtil.l("response.body()为空");
                            return;
                        }
                        if (!"success".equals(resultNews.getMessage())) {
                            ToastUtil.s(mCategory + "新闻获取失败");
                            LogUtil.l(mCategory + "新闻获取失败");
                            return;
                        }
                        List<NewsData> datas = resultNews.getData();
                        List<NewsContent> newsContents = new ArrayList<>();
                        for (NewsData data : datas) {
                            String contentJson = data.getContent();
                            NewsContent content = new Gson().fromJson(contentJson, NewsContent.class);
                            if (TextUtils.isEmpty(content.getTitle())) {
                                continue;
                            }
                            newsContents.add(content);
                        }
                        setData(newsContents);
                    }

                    @Override
                    public void onFailure(Call<ResultNews> call, Throwable t) {
                        srlRefresh.setRefreshing(false);
                        ToastUtil.s(mCategory + "新闻请求失败" + t.getMessage());
                        LogUtil.l(mCategory + "新闻请求失败" + t.getMessage());
                    }
                });
    }

    public void setData(List<NewsContent> newsContents) {
        if (newsContents == null || newsContents.size() == 0) {
            llNothing.setVisibility(View.VISIBLE);
            return;
        }
        llNothing.setVisibility(View.GONE);
//        mNewsContent.clear();
//        mNewsContent.addAll(newsContents);
        mAdapter.replaceData(newsContents);
    }

    private String getRelativeTime(long old_time) {
        long time = new Date().getTime();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(time);
        String old_date = new SimpleDateFormat("yyyy-MM-dd").format(old_time);
        String year_month = date.substring(0, 7); // 当前时间 年月
        String old_year_month = old_date.substring(0, 7); // 待测时间 年月
        int day = Integer.parseInt(date.substring(8)); // 当前时间 日
        int old_day = Integer.parseInt(old_date.substring(8)); // 待测时间 日
        if (year_month.equals(old_year_month)) { // 如果年月相同
            switch (day - old_day) {
                case 0:
                    return new SimpleDateFormat("HH:mm").format(old_time);
                case 1:
                    return "昨天 " + new SimpleDateFormat("HH:mm").format(old_time);
                default:
                    break;
            }
        }
        return new SimpleDateFormat("yyyy-M-d HH:mm").format(old_time);
    }
}
