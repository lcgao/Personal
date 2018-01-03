package com.lcgao.personal.home.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.personal.R;
import com.lcgao.personal.util.ToastUtil;

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

public class WebViewFragment extends Fragment {
    @BindView(R.id.wv_fragment_webview)
    WebView webView;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private ZhihuService service = retrofit.create(ZhihuService.class);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
//        webView.loadUrl("file:///android_asset/index.html");
        service.getNewsInfo(bundle.getString("id"))
                .clone()
                .enqueue(new Callback<NewsInfo>() {
                    @Override
                    public void onResponse(Call<NewsInfo> call, Response<NewsInfo> response) {
                        NewsInfo newsInfo = response.body();
                        if(newsInfo == null){
                            ToastUtil.s("response.body()为空");
                            return;
                        }
                        webView.loadUrl(newsInfo.getShare_url());

                    }

                    @Override
                    public void onFailure(Call<NewsInfo> call, Throwable t) {

                    }
                });
        return view;
    }

}
