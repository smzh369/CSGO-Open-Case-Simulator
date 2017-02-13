package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by 令子 on 2017/2/13.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;

}
