package com.zerlings.gabeisfaker.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.activity.SimulatorActivity;

import java.util.List;

/**
 * Created by 令子 on 2017/2/15.
 */

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.ViewHolder> {

    private Context mContext;

    private List<Case> mCaseList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView caseImage;
        TextView caseName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            caseImage = (ImageView)view.findViewById(R.id.case_image);
            caseName = (TextView)view.findViewById(R.id.case_name);
        }
    }

    public CaseAdapter(List<Case> caseList){
        mCaseList = caseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.case_item,parent,false);
        final ViewHolder holder =  new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Case mCase = mCaseList.get(position);
                Intent intent = new Intent(mContext,SimulatorActivity.class);
                intent.putExtra(SimulatorActivity.CASE_NAME,mCase.getCaseName());
                intent.putExtra(SimulatorActivity.CASE_IMAGE_ID,mCase.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Case mCase = mCaseList.get(position);
        Glide.with(mContext).load(mCase.getImageId()).into(holder.caseImage);
        holder.caseName.setText(mCase.getCaseName());
    }

    @Override
    public int getItemCount() {
        return mCaseList.size();
    }
}
