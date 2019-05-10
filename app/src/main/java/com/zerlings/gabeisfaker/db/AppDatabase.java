package com.zerlings.gabeisfaker.db;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.util.List;


/**
 * Created by smzh369 on 2017/2/13.
 */

@Database(version = AppDatabase.VERSION)
public class AppDatabase {

    public static final int VERSION = 3;

    @Migration(version = 0, database = AppDatabase.class)
    public static class Migration0 extends BaseMigration {

        @Override
        public void migrate(@NonNull DatabaseWrapper database) {
            List<Knife> allKnives = InitUtils.initKnife();
            FastStoreModelTransaction transaction1 = FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(Knife.class))
                    .addAll(allKnives).build();
            transaction1.execute(database);
            List<Glove> allGloves = InitUtils.initGlove();
            FastStoreModelTransaction transaction2 = FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(Glove.class))
                    .addAll(allGloves).build();
            transaction2.execute(database);
        }
    }

    @Migration(version = VERSION, database = AppDatabase.class)
    public static class Migration1 extends BaseMigration {

        @Override
        public void migrate(@NonNull DatabaseWrapper database) {
            if (database.getVersion() < 3){
                database.execSQL("drop table if exists Knife");
                database.execSQL("drop table if exists Glove");
                database.execSQL("drop table if exists UniqueItem");
                database.execSQL("create table Knife ("
                        + "knifeName text, "
                        + "skinName text, "
                        + "imageName text primary key, "
                        + "minWear real, "
                        + "maxWear real)");
                database.execSQL("create table Glove ("
                        + "gloveName text, "
                        + "skinName text, "
                        + "imageName text primary key, "
                        + "minWear real, "
                        + "maxWear real)");
                database.execSQL("create table UniqueItem ("
                        + "id integer primary key autoincrement,"
                        + "itemName text, "
                        + "skinName text, "
                        + "imageName text, "
                        + "quality integer, "
                        + "exterior text, "
                        + "wearValue real, "
                        + "isStatTrak integer default 0, "
                        + "itemType integer)");
                List<Knife> allKnives = InitUtils.initKnife();
                FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(Knife.class))
                        .addAll(allKnives)
                        .build()
                        .execute(database);
                List<Glove> allGloves = InitUtils.initGlove();
                FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(Glove.class))
                        .addAll(allGloves)
                        .build()
                        .execute(database);
            }

        }
    }

}
