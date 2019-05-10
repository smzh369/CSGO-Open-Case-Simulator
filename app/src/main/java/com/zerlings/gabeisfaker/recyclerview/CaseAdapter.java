package com.zerlings.gabeisfaker.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.Case;

import java.util.List;

/**
 * Created by smzh369 on 2017/2/15.
 */

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.ViewHolder> {

    private Context mContext;

    private List<Case> mCaseList;

    private int brId;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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

    public CaseAdapter(List<Case> caseList,int brId){
        mCaseList = caseList;
        this.brId = brId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.case_item,parent,false);
        ViewHolder holder =  new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.getBinding().setVariable(brId,mCaseList.get(position));
        holder.getBinding().executePendingBindings();
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCaseList.size();
    }
}
