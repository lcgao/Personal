package com.lcgao.personal.favourite.news;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by lcgao on 2018/1/27.
 */

public interface NewsService {
    @GET
    Call<ResultNews> getNews(@Url String url);
}
