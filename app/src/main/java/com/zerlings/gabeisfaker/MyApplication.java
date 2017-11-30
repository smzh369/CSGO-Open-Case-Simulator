package com.zerlings.gabeisfaker;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.zerlings.gabeisfaker.db.AppDatabase;

/**
 * Created by 令子 on 2017/2/13.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(FlowConfig.builder(this)
                .addDatabaseConfig(DatabaseConfig.builder(AppDatabase.class)
                .databaseName("AppDatabase")
                .build())
                .build());
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
