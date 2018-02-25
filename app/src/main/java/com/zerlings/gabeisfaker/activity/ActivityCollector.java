package com.zerlings.gabeisfaker.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 令子 on 2018/2/25.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }

    }
}
