package com.zerlings.gabeisfaker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.Case;
import com.zerlings.gabeisfaker.utils.CaseAdapter;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 令子 on 2017/2/15.
 */

public class CaseFragment extends Fragment {

    private Button settingButton;

    private DrawerLayout drawerLayout;

    private RecyclerView recyclerView;

    private CaseAdapter adapter;

    private List<Case> caseList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_case,container,false);
        settingButton = (Button)view.findViewById(R.id.setting);
        drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),4);
        recyclerView.setLayoutManager(layoutManager);
        caseList = InitUtils.initCase();
        adapter = new CaseAdapter(caseList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}
