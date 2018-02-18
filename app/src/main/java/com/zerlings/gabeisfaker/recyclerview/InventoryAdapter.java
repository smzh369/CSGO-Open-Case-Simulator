package com.zerlings.gabeisfaker.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.activity.InventoryActivity;
import com.zerlings.gabeisfaker.databinding.UniqueItemBinding;
import com.zerlings.gabeisfaker.db.UniqueItem;

import java.util.List;
import java.util.Set;

/**
 * Created by 令子 on 2017/2/19.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder>{

    private static final int TYPE_NORMAL = 0;

    private static final int TYPE_STATTRAK = 1;

    private Context mContext;

    private List<UniqueItem> mItemList;

    private int brId;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding;
        RelativeLayout itemLayout;

        ViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

    }

    public InventoryAdapter(List<UniqueItem> itemList, int brId){
        mItemList = itemList;
        this.brId = brId;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItemList.get(position).isStatTrak()){
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
        UniqueItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.unique_item,parent,false);
        ViewHolder holder =  new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        holder.itemLayout = binding.itemLayout;
        return holder;
    }

    @Override
    public void onBindViewHolder(final InventoryAdapter.ViewHolder holder, final int position) {
        Set<Integer> positionSet = InventoryActivity.positionSet;
        if (positionSet.contains(position)) {
            holder.itemLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.selected));
        } else {
            holder.itemLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.unSelected));
        }
        holder.getBinding().setVariable(brId,mItemList.get(position));
        holder.getBinding().executePendingBindings();
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(v,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }
}
