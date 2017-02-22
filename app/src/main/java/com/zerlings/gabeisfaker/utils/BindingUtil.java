package com.zerlings.gabeisfaker.utils;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.zerlings.gabeisfaker.MyApplication;
import com.zerlings.gabeisfaker.R;

/**
 * Created by 令子 on 2017/2/22.
 */

public class BindingUtil {

    @BindingAdapter("imageId")
    public static void setImage(ImageView imageView, int imageId){
        Glide.with(MyApplication.getContext()).load(imageId).into(imageView);
    }

    @BindingAdapter("visibility")
    public static void setvisibility(ImageView imageView, boolean isStatTrak){
        if (isStatTrak){
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }
    }
    @BindingAdapter("layoutColor")
    public static void setColor(LinearLayout qualityLayout, int quality){

        switch (quality){
            case 7:qualityLayout.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.knife));
                break;
            case 6:qualityLayout.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(),R.color.convert));
                break;
            case 5:qualityLayout.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(),R.color.classified));
                break;
            case 4:qualityLayout.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(),R.color.restricted));
                break;
            case 3:qualityLayout.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(),R.color.milspec));
                break;
            default:break;
        }
    }

}
