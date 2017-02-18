package com.zerlings.gabeisfaker.recyclerview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zerlings.gabeisfaker.R;

import java.util.List;

/**
 * Created by 令子 on 2017/2/16.
 */

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.ViewHolder> {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_STATTRAK = 2;

    private View mHeaderView;

    private Context mContext;

    private List<Weapon> mWeaponList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout qualityLayout;
        ImageView weaponImage;
        TextView weaponName;
        TextView skinName;

        public ViewHolder(View view){
            super(view);
            qualityLayout = (LinearLayout)view.findViewById(R.id.quality_layout);
            weaponImage = (ImageView)view.findViewById(R.id.weapon_image);
            weaponName = (TextView)view.findViewById(R.id.weapon_name);
            skinName = (TextView)view.findViewById(R.id.skin_name);
        }
    }
    public WeaponAdapter(List<Weapon> weaponList){
        mWeaponList = weaponList;
    }

    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (mWeaponList.get(position).isStatTrak()){
            return TYPE_STATTRAK;
        }
        if (mHeaderView == null){
            return TYPE_NORMAL;
        }
        if (position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public WeaponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new WeaponAdapter.ViewHolder(mHeaderView);
        }
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.weapon_item,parent,false);
        if (viewType == TYPE_STATTRAK){
            ImageView statTrakView = (ImageView) view.findViewById(R.id.st_img);
            statTrakView.setVisibility(View.VISIBLE);
        }
        final WeaponAdapter.ViewHolder holder =  new WeaponAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WeaponAdapter.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NORMAL || getItemViewType(position) == TYPE_STATTRAK){
            Weapon weapon = mWeaponList.get(position);
            Glide.with(mContext).load(weapon.getImageId()).into(holder.weaponImage);
            holder.weaponName.setText(weapon.getWeaponName());
            holder.skinName.setText(weapon.getSkinName());
            switch (weapon.getQuality()){
                case 7:holder.qualityLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.knife));
                    break;
                case 6:holder.qualityLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.convert));
                    break;
                case 5:holder.qualityLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.classified));
                    break;
                case 4:holder.qualityLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.restricted));
                    break;
                case 3:holder.qualityLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.milspec));
                    break;
                default:break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mWeaponList.size();
    }
}
