package com.zerlings.gabeisfaker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.recyclerview.CustomLinearLayoutManager;
import com.zerlings.gabeisfaker.recyclerview.Weapon;
import com.zerlings.gabeisfaker.utils.DensityUtil;
import com.zerlings.gabeisfaker.utils.InitUtils;
import com.zerlings.gabeisfaker.recyclerview.SpaceItemDecoration;
import com.zerlings.gabeisfaker.recyclerview.WeaponAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 令子 on 2017/2/15.
 */

public class SimulatorActivity extends AppCompatActivity {

    public static final String CASE_NAME = "case_name";

    public static final String CASE_IMAGE_ID = "case_image_id";

    private RecyclerView recyclerView;

    private WeaponAdapter adapter;

    private List<Weapon> weapons;

    private List<Weapon> weaponList = new ArrayList<>();
    private List<Weapon> convertList = new ArrayList<>();
    private List<Weapon> classifiedList = new ArrayList<>();
    private List<Weapon> restrictedList = new ArrayList<>();
    private List<Weapon> milspecList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.simulator_activity);
        Button backButton = (Button) findViewById(R.id.back);
        Button startButton = (Button)findViewById(R.id.start_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_2);
        TextView titleText = (TextView)findViewById(R.id.title_text);

        Intent intent = getIntent();
        titleText.setText(intent.getStringExtra(CASE_NAME));
        int caseImageId = intent.getIntExtra(CASE_IMAGE_ID,0);

        //初始化各项列表
        weapons = InitUtils.initWeapon(caseImageId);
        convertList.clear();
        classifiedList.clear();
        restrictedList.clear();
        milspecList.clear();
        for (int i = 0;i<weapons.size();i++) {
            Weapon weapon = weapons.get(i);
            if (weapon.getQuality() == 6) {
                convertList.add(weapon);
            } else if (weapon.getQuality() == 5) {
                classifiedList.add(weapon);
            } else if (weapon.getQuality() == 4) {
                restrictedList.add(weapon);
            } else {
                milspecList.add(weapon);
            }
        }
        initList();

        adapter = new WeaponAdapter(weaponList);

        //设置recyclerview显示方式
        final CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        //设置item间隔
        int spacingInPixels = DensityUtil.dip2px(7.5f);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        //添加表头
        View header = LayoutInflater.from(this).inflate(R.layout.item_header,recyclerView,false);
        adapter.setHeaderView(header);
        recyclerView.setAdapter(adapter);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager.setSpeed(0.6f);
                recyclerView.smoothScrollToPosition(38);
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    private void initList(){
        weaponList.clear();
        Random random = new Random();
        for (int i = 0;i<40;i++){
            int index = random.nextInt(weapons.size());
            int st = random.nextInt(10);
            Weapon weapon = weapons.get(index);
            if (st == 0){
                weapon.setStatTrak(true);
            }
            weaponList.add(weapon);
        }
        //计算最终得到的物品
        int degree = random.nextInt(500);
        if (degree == 499){
            weaponList.get(37).setStatTrak(false);
            weaponList.get(37).setWeaponName("*Rare Special Item*");
            weaponList.get(37).setImageId(R.drawable.rare_special);
            weaponList.get(37).setSkinName(null);
            weaponList.get(37).setQuality(7);
        }else if (degree > 495 && degree < 499){
            weaponList.set(37,convertList.get(random.nextInt(convertList.size())));
        }else if (degree > 480 && degree < 496){
            weaponList.set(37,classifiedList.get(random.nextInt(classifiedList.size())));
        }else if (degree > 400 && degree < 481){
            weaponList.set(37,restrictedList.get(random.nextInt(restrictedList.size())));
        }else {
            weaponList.set(37,milspecList.get(random.nextInt(milspecList.size())));
        }
    }
}
