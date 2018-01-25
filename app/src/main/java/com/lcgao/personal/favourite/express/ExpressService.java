package com.lcgao.personal.favourite.express;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lcgao on 2018/1/25.
 */

public interface ExpressService {
    @GET("query")
    Call<ExpressInfo> searchExpress(@QueryMap Map<String, String> map);
}
