package com.zerlings.gabeisfaker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.activity.MainActivity;
import com.zerlings.gabeisfaker.activity.SimulatorActivity;
import com.zerlings.gabeisfaker.recyclerview.Case;
import com.zerlings.gabeisfaker.recyclerview.CaseAdapter;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 令子 on 2017/2/15.
 */

public class CaseFragment extends Fragment {

    private Button settingButton;

    private RecyclerView recyclerView;

    private CaseAdapter adapter;

    private List<Case> caseList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_case,container,false);
        settingButton = (Button)view.findViewById(R.id.left_button);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        if (getActivity() instanceof MainActivity){
            GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),4);
            recyclerView.setLayoutManager(layoutManager);
        }else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            settingButton.setVisibility(View.GONE);
            TextView titleText = (TextView)view.findViewById(R.id.title_text);
            titleText.setText(R.string.another_case);
        }
        caseList = InitUtils.initCase();
        adapter = new CaseAdapter(caseList);
        recyclerView.setAdapter(adapter);
        settingButton.setBackgroundResource(R.drawable.ic_cs);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = (DrawerLayout)v.getRootView().findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        adapter.setOnItemClickListener(new CaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Case mCase = caseList.get(position);
                if (getActivity() instanceof MainActivity){
                    Intent intent = new Intent(getActivity(),SimulatorActivity.class);
                    intent.putExtra(SimulatorActivity.CASE_NAME,mCase.getCaseName());
                    intent.putExtra(SimulatorActivity.CASE_IMAGE_ID,mCase.getImageId());
                    startActivity(intent);
                }else {
                    SimulatorActivity activity = (SimulatorActivity)getActivity();
                    activity.drawerLayout.closeDrawers();
                    activity.caseImageId = mCase.getImageId();
                    activity.titleText.setText(mCase.getCaseName());
                    activity.initWeapons();
                    activity.initList();
                    activity.adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
