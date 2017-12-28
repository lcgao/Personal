package com.lcgao.home.zhihu;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lcgao on 2017/12/28.
 */

public interface ZhihuService {

    @GET("api/4/news/latest")
    Call<ResultZhihu> getLatestZhihu();
}
