package com.zerlings.gabeisfaker.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.WindowManager;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.databinding.InventoryActivityBinding;
import com.zerlings.gabeisfaker.db.UniqueWeapon;
import com.zerlings.gabeisfaker.recyclerview.InventoryAdapter;
import com.zerlings.gabeisfaker.recyclerview.InventoryItemDecoration;
import com.zerlings.gabeisfaker.utils.DensityUtil;
import com.zerlings.gabeisfaker.BR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 令子 on 2017/2/19.
 */

public class InventoryActivity extends AppCompatActivity{

    public static Set<Integer> positionSet = new HashSet<>();

    private InventoryAdapter adapter;

    private List<UniqueWeapon> uniqueWeaponList = new ArrayList<>();

    private Boolean selectMode = false;

    private InventoryActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this,R.layout.inventory_activity);

        //初始化各组件
        binding.inventoryTitle.titleText.setText(R.string.inventory);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.recyclerView3.setLayoutManager(layoutManager);
        int spacingInPixels = DensityUtil.dip2px(11f);
        binding.recyclerView3.addItemDecoration(new InventoryItemDecoration(spacingInPixels));
        uniqueWeaponList = SQLite.select().from(UniqueWeapon.class).queryList();
        adapter = new InventoryAdapter(uniqueWeaponList,BR.uniqueWeapon);
        binding.recyclerView3.setAdapter(adapter);

        binding.inventoryTitle.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectMode){
                    selectMode = false;
                    positionSet.clear();
                    adapter.notifyDataSetChanged();
                    binding.inventoryTitle.rightButton.setVisibility(View.GONE);
                }else {
                    finish();
                }
            }
        });
        binding.inventoryTitle.rightButton.setBackgroundResource(R.drawable.ic_delete);
        binding.inventoryTitle.rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除已选
                List<UniqueWeapon> uniqueWeapons = new ArrayList<>();
                uniqueWeapons.addAll(uniqueWeaponList);
                for (int position : positionSet) {
                    UniqueWeapon weapon = uniqueWeapons.get(position);
                    int newPosition = uniqueWeaponList.indexOf(weapon);
                    uniqueWeaponList.remove(newPosition);
                    adapter.notifyItemRemoved(newPosition);
                    adapter.notifyItemRangeChanged(newPosition,uniqueWeaponList.size() - newPosition);
                    weapon.delete();
                }
                positionSet.clear();
                selectMode = false;
                binding.inventoryTitle.rightButton.setVisibility(View.GONE);
            }
        });

        adapter.setOnItemClickListener(new InventoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (selectMode) {
                    // 如果当前处于多选状态，则进入多选状态的逻辑
                    // 维护当前已选的position
                    addOrRemove(position);
                } else {
                    // 如果不是多选状态，则进入点击事件的业务逻辑
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (!selectMode) {
                    selectMode = true;
                    binding.inventoryTitle.rightButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void addOrRemove(int position) {
        if (positionSet.contains(position)) {
            // 如果包含，则撤销选择
            positionSet.remove(position);
        } else {
            // 如果不包含，则添加
            positionSet.add(position);
        }
        if (positionSet.size() == 0) {
            // 如果没有选中任何的item，则退出多选模式
            adapter.notifyDataSetChanged();
            binding.inventoryTitle.rightButton.setVisibility(View.GONE);
            selectMode = false;
        } else {
            // 更新列表界面，否则无法显示已选的item
            adapter.notifyDataSetChanged();
        }
    }

}
