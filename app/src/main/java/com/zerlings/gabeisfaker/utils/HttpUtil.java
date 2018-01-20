package com.zerlings.gabeisfaker.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 令子 on 2018/1/8.
 */

public class HttpUtil {

    public static QueryItem retrofitConnection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://steamcommunity.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(QueryItem.class);
    }

}
