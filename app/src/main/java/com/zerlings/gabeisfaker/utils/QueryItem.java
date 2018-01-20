package com.zerlings.gabeisfaker.utils;

import com.zerlings.gabeisfaker.gson.Sale;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 令子 on 2018/1/8.
 */

public interface QueryItem {

    @GET("market/priceoverview/")
    Observable<Sale> queryLowestPrice(@QueryMap Map<String,String> map,@Query("market_hash_name") String marketHashName);

}
