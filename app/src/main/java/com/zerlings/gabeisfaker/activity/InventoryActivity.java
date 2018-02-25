package com.zerlings.gabeisfaker.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.databinding.InventoryActivityBinding;
import com.zerlings.gabeisfaker.db.UniqueItem;
import com.zerlings.gabeisfaker.gson.Sale;
import com.zerlings.gabeisfaker.recyclerview.InventoryAdapter;
import com.zerlings.gabeisfaker.recyclerview.InventoryItemDecoration;
import com.zerlings.gabeisfaker.utils.DensityUtil;
import com.zerlings.gabeisfaker.BR;
import com.zerlings.gabeisfaker.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 令子 on 2017/2/19.
 */

public class InventoryActivity extends BaseActivity{

    public static Set<Integer> positionSet = new HashSet<>();

    private InventoryAdapter adapter;

    private List<UniqueItem> uniqueItemList = new ArrayList<>();

    private UniqueItem uniqueItem;

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
        binding.inventoryTitle.leftButton.setBackgroundResource(R.drawable.ic_back);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.recyclerView3.setLayoutManager(layoutManager);
        int spacingInPixels = DensityUtil.dip2px(11f);
        binding.recyclerView3.addItemDecoration(new InventoryItemDecoration(spacingInPixels));
        uniqueItemList = SQLite.select().from(UniqueItem.class).queryList();
        positionSet.clear();
        adapter = new InventoryAdapter(uniqueItemList,BR.uniqueItem);
        binding.recyclerView3.setAdapter(adapter);

        binding.inventoryTitle.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.inventoryTitle.rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectMode){
                    // 删除已选
                    List<UniqueItem> uniqueItems = new ArrayList<>();
                    uniqueItems.addAll(uniqueItemList);
                    for (int position : positionSet) {
                        UniqueItem weapon = uniqueItems.get(position);
                        int newPosition = uniqueItemList.indexOf(weapon);
                        uniqueItemList.remove(newPosition);
                        adapter.notifyItemRemoved(newPosition);
                        adapter.notifyItemRangeChanged(newPosition,uniqueItemList.size() - newPosition);
                        weapon.delete();
                    }
                    positionSet.clear();
                    selectMode = false;
                    binding.inventoryTitle.rightButton.setVisibility(View.GONE);
                } else {
                    //查询价格
                    binding.progressBarLayout.setVisibility(View.VISIBLE);
                    Map<String,String> marketMap = new HashMap<>();
                    marketMap.put("appid","730");
                    marketMap.put("currency","1");
                    String marketHashName = uniqueItem.getItemName()
                            + " | "
                            + uniqueItem.getSkinName()
                            + " ("
                            + uniqueItem.getExterior()
                            + ")";
                    if (uniqueItem.isStatTrak()){
                        marketHashName = "StatTrak™ " + marketHashName;
                    }
                    HttpUtil.retrofitConnection()
                            .queryLowestPrice(marketMap,marketHashName)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Sale>() {

                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Sale sale) {
                                    Toast.makeText(InventoryActivity.this,
                                            "Price: " + sale.getLowest_price(),
                                            Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                    Toast.makeText(InventoryActivity.this,
                                            "Network Error !（由于steamcommunity被封，查询价格需要翻墙）",
                                            Toast.LENGTH_SHORT).show();
                                    binding.progressBarLayout.setVisibility(View.GONE);
                                }

                                @Override
                                public void onComplete() {
                                    binding.progressBarLayout.setVisibility(View.GONE);
                                }
                            });
                }

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
                    // 如果不是多选状态，则进入单选事件的业务逻辑
                    uniqueItem = uniqueItemList.get(position);
                    if (positionSet.isEmpty()){
                        //第一次选取，显示查询图标
                        binding.inventoryTitle.rightButton.setBackgroundResource(R.drawable.ic_query);
                        binding.inventoryTitle.rightButton.setVisibility(View.VISIBLE);
                    }
                    if (!positionSet.contains(position)){
                        // 选择不同的单位时取消之前选中的单位
                        positionSet.clear();
                    }
                    addOrRemove(position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (!selectMode) {
                    selectMode = true;
                    positionSet.clear();
                    binding.inventoryTitle.rightButton.setBackgroundResource(R.drawable.ic_delete);
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

    @Override
    public void onBackPressed() {
        if (selectMode){
            selectMode = false;
            positionSet.clear();
            adapter.notifyDataSetChanged();
            binding.inventoryTitle.rightButton.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }
    }
}
