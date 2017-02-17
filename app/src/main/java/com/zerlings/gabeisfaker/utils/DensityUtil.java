package com.zerlings.gabeisfaker.utils;

import android.content.Context;

import com.zerlings.gabeisfaker.MyApplication;

/**
 * Created by 令子 on 2017/2/17.
 */

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px( float dpValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip( float pxValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
