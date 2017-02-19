package com.zerlings.gabeisfaker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.UniqueWeapon;
import com.zerlings.gabeisfaker.recyclerview.CustomLinearLayoutManager;
import com.zerlings.gabeisfaker.recyclerview.Weapon;
import com.zerlings.gabeisfaker.recyclerview.WeaponItemDecoration;
import com.zerlings.gabeisfaker.utils.DensityUtil;
import com.zerlings.gabeisfaker.utils.InitUtils;
import com.zerlings.gabeisfaker.recyclerview.WeaponAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 令子 on 2017/2/15.
 */

public class SimulatorActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String CASE_NAME = "case_name";

    public static final String CASE_IMAGE_ID = "case_image_id";

    private RecyclerView recyclerView;

    private WeaponAdapter adapter;

    private LinearLayout qualityLayout;

    private RelativeLayout uniqueWeaponLayout;

    private ImageView weaponImage;

    private ImageView statTrakImage;

    private TextView weaponName;

    private TextView skinName;

    private TextView exteriorText;

    private UniqueWeapon uniqueWeapon;

    private List<Weapon> weapons;

    private List<Weapon> weaponList = new ArrayList<>();
    private List<Weapon> convertList = new ArrayList<>();
    private List<Weapon> classifiedList = new ArrayList<>();
    private List<Weapon> restrictedList = new ArrayList<>();
    private List<Weapon> milspecList = new ArrayList<>();

    private Handler handler = new Handler(){

        //更新开出物品的视图
        @Override
        public void handleMessage(Message msg) {
            uniqueWeapon = (UniqueWeapon) msg.obj;
            weaponName.setText(uniqueWeapon.getWeaponName());
            skinName.setText(uniqueWeapon.getSkinName());
            exteriorText.setText(uniqueWeapon.getExterior());
            Glide.with(SimulatorActivity.this).load(uniqueWeapon.getImageId()).into(weaponImage);
            if (uniqueWeapon.isStatTrak()){
                statTrakImage.setVisibility(View.VISIBLE);
            }else {
                statTrakImage.setVisibility(View.GONE);
            }
            switch (msg.what){
                case 3:
                    qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.milspec));
                    break;
                case 4:
                    qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.restricted));
                    break;
                case 5:
                    qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.classified));
                    break;
                case 6:
                    qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.convert));
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.simulator_activity);

        //初始化布局
        Button backButton = (Button) findViewById(R.id.left_button);
        Button inventoryButton = (Button)findViewById(R.id.right_button);
        inventoryButton.setVisibility(View.VISIBLE);
        Button startButton = (Button)findViewById(R.id.start_button);
        Button discardButton = (Button)findViewById(R.id.discard_button);
        Button keepButton = (Button)findViewById(R.id.keep_button);
        qualityLayout = (LinearLayout)findViewById(R.id.quality_layout);
        uniqueWeaponLayout = (RelativeLayout)findViewById(R.id.unique_weapon_layout);
        weaponImage = (ImageView)findViewById(R.id.weapon_image);
        statTrakImage = (ImageView)findViewById(R.id.st_img);
        weaponName = (TextView)findViewById(R.id.weapon_name);
        skinName = (TextView)findViewById(R.id.skin_name);
        exteriorText = (TextView)findViewById(R.id.exterior_text);
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
        for (int i = 0;i < weapons.size();i++) {
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

        //设置recyclerview显示方式
        adapter = new WeaponAdapter(weaponList);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setSpeed(0.6f);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        //设置item间隔
        int spacingInPixels = DensityUtil.dip2px(10f);
        recyclerView.addItemDecoration(new WeaponItemDecoration(spacingInPixels));

        //添加表头
        View header = LayoutInflater.from(this).inflate(R.layout.item_header,recyclerView,false);
        adapter.setHeaderView(header);

        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        uniqueWeaponLayout.setVisibility(View.VISIBLE);
                        exteriorText.setVisibility(View.VISIBLE);
                        initList();
                        adapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(0);
                        break;
                    default:break;
                }
            }
        });

        backButton.setOnClickListener(this);
        inventoryButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        discardButton.setOnClickListener(this);
        keepButton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.discard_button:
                uniqueWeaponLayout.setVisibility(View.GONE);
                break;
            case R.id.keep_button:
                uniqueWeapon.save();
                uniqueWeaponLayout.setVisibility(View.GONE);
                break;
            case R.id.left_button:
                finish();break;
            case R.id.right_button:
                Intent intent = new Intent(this,InventoryActivity.class);
                startActivity(intent);
                break;
            case R.id.start_button:
                final Weapon weapon = weaponList.get(37);
                recyclerView.smoothScrollToPosition(38);
                //recyclerView.smoothScrollBy(DensityUtil.dip2px(5445), 0);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UniqueWeapon uniqueWeapon = new UniqueWeapon();
                        Random random = new Random();
                        Message message = new Message();
                        uniqueWeapon.setWeaponName(weapon.getWeaponName());
                        uniqueWeapon.setSkinName(weapon.getSkinName());
                        uniqueWeapon.setQuality(weapon.getQuality());
                        uniqueWeapon.setImageId(weapon.getImageId());
                        uniqueWeapon.setStatTrak(weapon.isStatTrak());
                        float wear = random.nextFloat()*(weapon.getMaxWear()-weapon.getMinWear()) + weapon.getMinWear();
                        uniqueWeapon.setWearValue(wear);
                        if (wear>=0 && wear<7){
                            uniqueWeapon.setExterior(SimulatorActivity.this.getResources().getString(R.string.factory_new));
                        }else if (wear>=7 && wear<15){
                            uniqueWeapon.setExterior(SimulatorActivity.this.getResources().getString(R.string.minimal_wear));
                        }else if (wear>=15 && wear<38){
                            uniqueWeapon.setExterior(SimulatorActivity.this.getResources().getString(R.string.field_tested));
                        }else if (wear>=38 && wear<45){
                            uniqueWeapon.setExterior(SimulatorActivity.this.getResources().getString(R.string.well_worn));
                        }else {
                            uniqueWeapon.setExterior(SimulatorActivity.this.getResources().getString(R.string.battle_scarred));
                        }
                        switch (uniqueWeapon.getQuality()){
                            case 3:message.what = 3;break;
                            case 4:message.what = 4;break;
                            case 5:message.what = 5;break;
                            case 6:message.what = 6;break;
                            default:break;
                        }
                        message.obj = uniqueWeapon;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:break;
        }
    }
}
