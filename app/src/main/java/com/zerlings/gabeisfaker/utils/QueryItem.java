package com.zerlings.gabeisfaker.utils;

import com.zerlings.gabeisfaker.gson.Sale;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by smzh369 on 2018/1/8.
 */

public interface QueryItem {

    @GET("market/priceoverview/")
    Single<Sale> queryLowestPrice(@QueryMap Map<String,String> map, @Query("market_hash_name") String marketHashName);

}
