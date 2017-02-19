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
import com.zerlings.gabeisfaker.db.UniqueWeapon;

import java.util.List;

/**
 * Created by 令子 on 2017/2/19.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder>{

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_STATTRAK = 1;

    private Context mContext;

    private List<UniqueWeapon> mWeaponList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout qualityLayout;
        ImageView weaponImage;
        TextView weaponName;
        TextView skinName;
        TextView exteriorText;
        ImageView statTrakView;

        public ViewHolder(View view){
            super(view);
            qualityLayout = (LinearLayout)view.findViewById(R.id.quality_layout);
            weaponImage = (ImageView)view.findViewById(R.id.weapon_image);
            weaponName = (TextView)view.findViewById(R.id.weapon_name);
            skinName = (TextView)view.findViewById(R.id.skin_name);
            exteriorText = (TextView)view.findViewById(R.id.exterior_text);
            statTrakView = (ImageView) view.findViewById(R.id.st_img);
        }
    }

    public InventoryAdapter(List<UniqueWeapon> weaponList){
        mWeaponList = weaponList;
    }

    @Override
    public int getItemViewType(int position) {
        if (mWeaponList.get(position).isStatTrak()){
            return TYPE_STATTRAK;
        }else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public InventoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.weapon_item,parent,false);
        final InventoryAdapter.ViewHolder holder =  new InventoryAdapter.ViewHolder(view);
        if (viewType == TYPE_STATTRAK){
            holder.statTrakView.setVisibility(View.VISIBLE);
        }
        holder.exteriorText.setVisibility(View.VISIBLE);
        return holder;
    }

    @Override
    public void onBindViewHolder(InventoryAdapter.ViewHolder holder, int position) {

        UniqueWeapon weapon = mWeaponList.get(position);
        Glide.with(mContext).load(weapon.getImageId()).into(holder.weaponImage);
        holder.weaponName.setText(weapon.getWeaponName());
        holder.skinName.setText(weapon.getSkinName());
        holder.exteriorText.setText(weapon.getExterior());
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

    @Override
    public int getItemCount() {
        return mWeaponList.size();
    }
}
