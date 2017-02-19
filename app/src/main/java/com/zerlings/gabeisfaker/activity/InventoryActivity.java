package com.zerlings.gabeisfaker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.UniqueWeapon;
import com.zerlings.gabeisfaker.recyclerview.InventoryAdapter;
import com.zerlings.gabeisfaker.recyclerview.InventoryItemDecoration;
import com.zerlings.gabeisfaker.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 令子 on 2017/2/19.
 */

public class InventoryActivity extends AppCompatActivity {

    private Button backButton;

    private RecyclerView recyclerView;

    private InventoryAdapter adapter;

    private List<UniqueWeapon> uniqueWeaponList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.inventory_activity);

        //初始化各组件
        backButton = (Button)findViewById(R.id.left_button);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_3);
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        int spacingInPixels = DensityUtil.dip2px(11f);
        recyclerView.addItemDecoration(new InventoryItemDecoration(spacingInPixels));
        uniqueWeaponList = SQLite.select().from(UniqueWeapon.class).queryList();
        adapter = new InventoryAdapter(uniqueWeaponList);
        recyclerView.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
