package com.lcgao.personal;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.personal.home.zhihu.NewsInfo;
import com.lcgao.personal.home.zhihu.ZhihuService;
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
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private ZhihuService service = retrofit.create(ZhihuService.class);

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
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("url");
        String title = bundle.getString("title");
        srl_refresh.setRefreshing(true);
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        service.getNewsInfo(bundle.getString("id"))
                .clone()
                .enqueue(new Callback<NewsInfo>() {
                    @Override
                    public void onResponse(Call<NewsInfo> call, Response<NewsInfo> response) {
                        srl_refresh.setRefreshing(false);
                        srl_refresh.setEnabled(false);

                        NewsInfo newsInfo = response.body();
                        if(newsInfo == null){
                            ToastUtil.s("response.body()为空");
                            return;
                        }

                        tvTitle.setText(newsInfo.getTitle());
                        webView.loadUrl(newsInfo.getShare_url());

                    }

                    @Override
                    public void onFailure(Call<NewsInfo> call, Throwable t) {
                        srl_refresh.setRefreshing(false);
                        srl_refresh.setEnabled(false);
                    }
                });
    }
}
