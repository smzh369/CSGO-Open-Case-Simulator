package com.zerlings.gabeisfaker.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
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

import com.bumptech.glide.Glide;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.databinding.SimulatorActivityBinding;
import com.zerlings.gabeisfaker.db.UniqueWeapon;
import com.zerlings.gabeisfaker.recyclerview.CustomLinearLayoutManager;
import com.zerlings.gabeisfaker.db.Weapon;
import com.zerlings.gabeisfaker.recyclerview.WeaponItemDecoration;
import com.zerlings.gabeisfaker.utils.DensityUtil;
import com.zerlings.gabeisfaker.utils.InitUtils;
import com.zerlings.gabeisfaker.recyclerview.WeaponAdapter;
import com.zerlings.gabeisfaker.BR;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 令子 on 2017/2/15.
 */

public class SimulatorActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String CASE_NAME = "case_name";

    public static final String CASE_IMAGE_ID = "case_image_id";

    public int caseImageId;

    public WeaponAdapter adapter;

    private UniqueWeapon uniqueWeapon;

    private List<Weapon> weapons;

    private SoundPool soundPool;

    private int soundID;

    private MediaPlayer player;

    private Random random = new Random();

    public SimulatorActivityBinding binding;

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
            binding.uniqueItem.weaponName.setText(uniqueWeapon.getWeaponName());
            binding.uniqueItem.skinName.setText(uniqueWeapon.getSkinName());
            binding.uniqueItem.exteriorText.setText(uniqueWeapon.getExterior());
            Glide.with(SimulatorActivity.this).load(uniqueWeapon.getImageId()).into(binding.uniqueItem.weaponImage);
            if (uniqueWeapon.isStatTrak()){
                binding.uniqueItem.stImg.setVisibility(View.VISIBLE);
            }else {
                binding.uniqueItem.stImg.setVisibility(View.GONE);
            }
            switch (msg.what){
                case 3:
                    binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.milspec));
                    break;
                case 4:
                    binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.restricted));
                    break;
                case 5:
                    binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.classified));
                    break;
                case 6:
                    binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.convert));
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this,R.layout.simulator_activity);
        binding.simulatorTitle.rightButton.setVisibility(View.VISIBLE);

        //初始化SoundPool和MediaPlayer
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 5);
        }
        soundID = soundPool.load(this,R.raw.button,1);
        player = MediaPlayer.create(this,R.raw.open_case);

        //获取传入的箱子信息
        Intent intent = getIntent();
        binding.simulatorTitle.titleText.setText(intent.getStringExtra(CASE_NAME));
        caseImageId = intent.getIntExtra(CASE_IMAGE_ID,0);
        initWeapons();//初始化各项武器列表
        initList();//初始化游戏列表

        //设置recyclerview显示方式
        adapter = new WeaponAdapter(weaponList,BR.weapon);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setSpeed(0.58f);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recyclerView2.setLayoutManager(layoutManager);
        int spacingInPixels = DensityUtil.dip2px(10f);//设置item间隔
        binding.recyclerView2.addItemDecoration(new WeaponItemDecoration(spacingInPixels));
        View header = LayoutInflater.from(this).inflate(R.layout.item_header,binding.recyclerView2,false);//添加表头
        adapter.setHeaderView(header);

        binding.recyclerView2.setAdapter(adapter);
        binding.recyclerView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        binding.recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        binding.uniqueWeaponLayout.setVisibility(View.VISIBLE);
                        binding.uniqueItem.exteriorText.setVisibility(View.VISIBLE);
                        initList();
                        adapter.notifyDataSetChanged();
                        binding.recyclerView2.scrollToPosition(0);
                        break;
                    default:break;
                }
            }
        });

        binding.simulatorTitle.leftButton.setOnClickListener(this);
        binding.simulatorTitle.rightButton.setOnClickListener(this);
        binding.startButton.setOnClickListener(this);
        binding.discardButton.setOnClickListener(this);
        binding.keepButton.setOnClickListener(this);

    }

    //初始化各类武器列表
    public void initWeapons(){
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
    }

    public void initList(){

        weaponList.clear();
        for (int i = 0;i<40;i++){
            int index = random.nextInt(weapons.size());
            int st = random.nextInt(10);
            Weapon weapon = weapons.get(index);
            if (st == 0){
                weapon.setStatTrak(true);
            }
            weaponList.add(weapon);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.discard_button:
                binding.uniqueWeaponLayout.setVisibility(View.GONE);
                break;
            case R.id.keep_button:
                uniqueWeapon.save();
                binding.uniqueWeaponLayout.setVisibility(View.GONE);
                break;
            case R.id.left_button:
                finish();break;
            case R.id.right_button:
                Intent intent = new Intent(this,InventoryActivity.class);
                startActivity(intent);
                break;
            case R.id.start_button:
                soundPool.play(soundID,1,1,5,0,1);
                //soundPool.play(soundID.get(1),1,1,5,0,1);
                player.start();
                binding.recyclerView2.smoothScrollToPosition(38);
                //recyclerView.smoothScrollBy(DensityUtil.dip2px(5445), 0);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        setUniqueWeapon();
                        Weapon weapon = weaponList.get(37);
                        UniqueWeapon uniqueWeapon = getUniqueWeapon(weapon);

                        //发送异步消息
                        Message message = new Message();
                        switch (uniqueWeapon.getQuality()){
                            case 3:message.what = 3;break;//根据物品品质渲染展示界面
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

    /**计算得到的最终物品除磨损值以外的各项属性并替换列表中的相应位置**/
    private void setUniqueWeapon(){
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

    /**计算磨损值以得到完整的最终物品**/
    private UniqueWeapon getUniqueWeapon(Weapon weapon){

        UniqueWeapon uniqueWeapon = new UniqueWeapon();
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
        return uniqueWeapon;
    }
}
