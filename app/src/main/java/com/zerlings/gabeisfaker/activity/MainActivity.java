package com.zerlings.gabeisfaker.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.zerlings.gabeisfaker.BR;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.databinding.ActivityMainBinding;
import com.zerlings.gabeisfaker.db.AppDatabase;
import com.zerlings.gabeisfaker.db.Case;
import com.zerlings.gabeisfaker.db.Glove;
import com.zerlings.gabeisfaker.db.Knife;
import com.zerlings.gabeisfaker.recyclerview.CaseAdapter;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.util.List;


public class MainActivity extends BaseActivity {

    public static Snackbar snackbar;

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        /*判断数据库是否完整，不完整则重新录入数据*/
        long count = SQLite.selectCountOf().from(Knife.class).count();
        if (count != 296){
            Delete.table(Knife.class);
            List<Knife> allKnives = InitUtils.initKnife();
            FastStoreModelTransaction transaction = FastStoreModelTransaction
                    .saveBuilder(FlowManager.getModelAdapter(Knife.class))
                    .addAll(allKnives).build();
            FlowManager.getDatabase(AppDatabase.class).executeTransaction(transaction);
        }
        count = SQLite.selectCountOf().from(Glove.class).count();
        if (count != 48){
            Delete.table(Glove.class);
            List<Glove> allGloves = InitUtils.initGlove();
            FastStoreModelTransaction transaction = FastStoreModelTransaction
                    .saveBuilder(FlowManager.getModelAdapter(Glove.class))
                    .addAll(allGloves).build();
            FlowManager.getDatabase(AppDatabase.class).executeTransaction(transaction);
        }

        /*清空统计数据*/
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("rare","0");
        editor.putString("convert","0");
        editor.putString("classified","0");
        editor.putString("restricted","0");
        editor.putString("milspec","0");
        editor.putString("total","0");
        editor.apply();

        /*初始化*/
        binding.mainTitle.titleText.setText(getString(R.string.app_name));
        /*ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams)binding.mainTitle.leftButton.getLayoutParams();
        params.leftMargin = DensityUtil.dip2px(23f);
        binding.mainTitle.leftButton.setLayoutParams(params);*/
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        binding.caseList.setLayoutManager(layoutManager);
        final List<Case> caseList = InitUtils.initCase();
        CaseAdapter adapter = new CaseAdapter(caseList, BR.case1);
        binding.caseList.setAdapter(adapter);

        binding.mainTitle.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (snackbar != null &&snackbar.isShown()){
                    snackbar.dismiss();
                }
                binding.mainDrawer.openDrawer(GravityCompat.START);
            }
        });

        adapter.setOnItemClickListener(new CaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (snackbar != null && snackbar.isShown()){
                    snackbar.dismiss();
                }else{
                    Case mCase = caseList.get(position);
                    Intent intent = new Intent(MainActivity.this,SimulatorActivity.class);
                    intent.putExtra(SimulatorActivity.CASE_NAME,mCase.getCaseName());
                    intent.putExtra(SimulatorActivity.CASE_IMAGE_ID,mCase.getImageId());
                    intent.putExtra(SimulatorActivity.RARE_ITEM_TYPE,mCase.getRareItemType());
                    intent.putExtra(SimulatorActivity.RARE_SKIN_TYPE,mCase.getRareSkinType());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }else if (binding.mainDrawer.isDrawerOpen(Gravity.START)){
            binding.mainDrawer.closeDrawers();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        snackbar = null;
        super.onDestroy();
    }
}
