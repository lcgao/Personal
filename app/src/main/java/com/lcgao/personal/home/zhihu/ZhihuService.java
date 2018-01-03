package com.lcgao.personal.home.zhihu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lcgao on 2017/12/28.
 */

public interface ZhihuService {

    @GET("api/4/news/latest")
    Call<ResultZhihu> getLatestZhihu();
    @GET("api/4/news/{id}")
    Call<NewsInfo> getNewsInfo(@Path("id") String id);
}
