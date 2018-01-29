package com.lcgao.personal.home.essay;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by lcgao on 2018/1/29.
 */

public interface EssayService {
    @GET("random")
    Call<ResponseBody> getEssay(@QueryMap Map<String, String> map);
}
