package com.zerlings.gabeisfaker.utils;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.activity.SimulatorActivity;

/**
 * Created by smzh369 on 2017/2/22.
 */

public class BindingUtil {

    @BindingAdapter("imageName")
    public static void setImage(ImageView imageView, String imageName){
        if (imageName != null){
            int imageId = imageView.getResources().getIdentifier(imageName,"drawable","com.zerlings.gabeisfaker");
            Glide.with(imageView.getContext())
                    .load(imageId)
                    .into(imageView);
        }
    }

    @BindingAdapter("layoutColor")
    public static void setColor(RelativeLayout qualityLayout, int quality){

        switch (quality){
            case SimulatorActivity.LEVEL_RARE:
                qualityLayout.setBackgroundColor(ContextCompat.getColor(qualityLayout.getContext(), R.color.rare));
                break;
            case SimulatorActivity.LEVEL_CONVERT:
                qualityLayout.setBackgroundColor(ContextCompat.getColor(qualityLayout.getContext(),R.color.convert));
                break;
            case SimulatorActivity.LEVEL_CLASSIFIED:
                qualityLayout.setBackgroundColor(ContextCompat.getColor(qualityLayout.getContext(),R.color.classified));
                break;
            case SimulatorActivity.LEVEL_RESTRICTED:
                qualityLayout.setBackgroundColor(ContextCompat.getColor(qualityLayout.getContext(),R.color.restricted));
                break;
            case SimulatorActivity.LEVEL_MILSPEC:
                qualityLayout.setBackgroundColor(ContextCompat.getColor(qualityLayout.getContext(),R.color.milspec));
                break;
            default:break;
        }
    }

}
