package com.zerlings.gabeisfaker.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.Gun;

import java.util.List;

/**
 * Created by 令子 on 2017/2/16.
 */

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 1;

    private static final int TYPE_NORMAL = 0;

    private View mHeaderView;

    private List<Gun> mWeaponList;

    private int brId;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding;

        public ViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    public WeaponAdapter(List<Gun> weaponList,int brId){
        mWeaponList = weaponList;
        this.brId = brId;
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

        if (position == 0 && mHeaderView != null){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public WeaponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType == TYPE_NORMAL){
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.weapon_item,parent,false);
        }else {
            return new ViewHolder(mHeaderView);
        }
        ViewHolder holder =  new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(WeaponAdapter.ViewHolder holder, int position) {
        if( getItemViewType(position) == TYPE_NORMAL ){
            holder.getBinding().setVariable(brId,mWeaponList.get(position));
            holder.getBinding().executePendingBindings();
        }

    }

    @Override
    public int getItemCount() {
        return mWeaponList.size();
    }
}
