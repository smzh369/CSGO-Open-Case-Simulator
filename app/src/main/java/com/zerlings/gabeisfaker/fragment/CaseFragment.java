package com.zerlings.gabeisfaker.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.activity.MainActivity;
import com.zerlings.gabeisfaker.activity.SimulatorActivity;
import com.zerlings.gabeisfaker.databinding.ChooseCaseBinding;
import com.zerlings.gabeisfaker.db.Case;
import com.zerlings.gabeisfaker.recyclerview.CaseAdapter;
import com.zerlings.gabeisfaker.utils.InitUtils;
import com.zerlings.gabeisfaker.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 令子 on 2017/2/15.
 */

public class CaseFragment extends Fragment {

    private CaseAdapter adapter;

    private List<Case> caseList = new ArrayList<>();

    private ChooseCaseBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.choose_case,container,false);
        binding.fragTitle.titleText.setText(getString(R.string.app_name));
        if (getActivity() instanceof MainActivity){
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),4);
            binding.recyclerView.setLayoutManager(layoutManager);
        }else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            binding.recyclerView.setLayoutManager(layoutManager);
            binding.fragTitle.leftButton.setVisibility(View.GONE);
            binding.fragTitle.titleText.setText(getString(R.string.another_case));
        }

        /*//判断数据库是否完整，不完整则重新录入数据
        long count = SQLite.selectCountOf().from(Weapon.class).count();
        if (count < 139){
            Delete.table(Weapon.class);
            List<Weapon> allWeapons = InitUtils.initWeapon();
            FastStoreModelTransaction transaction = FastStoreModelTransaction
                    .saveBuilder(FlowManager.getModelAdapter(Weapon.class))
                    .addAll(allWeapons).build();
            FlowManager.getDatabase(AppDatabase.class).executeTransaction(transaction);
        }*/

        caseList = InitUtils.initCase();
        adapter = new CaseAdapter(caseList,BR.case1);
        binding.recyclerView.setAdapter(adapter);
        binding.fragTitle.leftButton.setBackgroundResource(R.drawable.ic_cs);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.fragTitle.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = v.getRootView().findViewById(R.id.drawer_layout);
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
                    activity.binding.drawerLayout2.closeDrawers();
                    activity.caseImageId = mCase.getImageId();
                    activity.binding.simulatorTitle.titleText.setText(mCase.getCaseName());
                    activity.initWeapons();
                    activity.initList();
                    activity.adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
