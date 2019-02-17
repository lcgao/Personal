package com.lcgao.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.home.zhihu.NewsInfo;
import com.lcgao.personal.home.zhihu.ZhihuService;
import com.lcgao.personal.profile.recent_read.RecentReadEntity;
import com.lcgao.personal.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebActivity extends BaseActivity {
    @BindView(R.id.webview_activity_web)
    WebView webView;
    @BindView(R.id.tb_activity_web)
    Toolbar toolbar;
    @BindView(R.id.srl_activity_web)
    SwipeRefreshLayout srl_refresh;
    @BindView(R.id.tv_activity_web_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(WebActivity.this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);

        srl_refresh.setColorSchemeColors(getResources().getColor(R.color.themecolor)
                , getResources().getColor(R.color.colorAccent));
        srl_refresh.setRefreshing(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("url");
        final String title = bundle.getString("title");
        final String content = bundle.getString("content");
        String id = bundle.getString("id");
        if (TextUtils.isEmpty(id)) {
            load(url, title, content);
            return;
        }
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ZhihuService service = retrofit.create(ZhihuService.class);
        service.getNewsInfo(id)
                .clone()
                .enqueue(new Callback<NewsInfo>() {
                    @Override
                    public void onResponse(Call<NewsInfo> call, Response<NewsInfo> response) {
//                        srl_refresh.setRefreshing(false);
//                        srl_refresh.setEnabled(false);

                        NewsInfo newsInfo = response.body();
                        if (newsInfo == null) {
                            ToastUtil.s("response.body()为空");
                            return;
                        }
                        load(newsInfo.getShare_url(), newsInfo.getTitle(), "");

                    }

                    @Override
                    public void onFailure(Call<NewsInfo> call, Throwable t) {
                        srl_refresh.setRefreshing(false);
                        srl_refresh.setEnabled(false);
                    }
                });
    }

    private void load(String url, String title, String content) {

        RecentReadEntity.addToReadHistory(new RecentReadEntity(url, title, content));
        tvTitle.setText(title);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);  //加上这一行网页为响应式的
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;   //返回true， 立即跳转，返回false,打开网页有延时
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    srl_refresh.setRefreshing(false);
                    srl_refresh.setEnabled(false);
                }
            }
        });
    }
}
