package com.zerlings.gabeisfaker.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
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
import com.zerlings.gabeisfaker.recyclerview.WeaponAdapter;
import com.zerlings.gabeisfaker.BR;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by 令子 on 2017/2/15.
 */

public class SimulatorActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String CASE_NAME = "case_name";

    public static final String CASE_IMAGE_ID = "case_image_id";

    public static final int LEVEL_RARE = 7;

    public static final int LEVEL_CONVERT = 6;

    public static final int LEVEL_CLASSIFIED = 5;

    public static final int LEVEL_RESTRICTED = 4;

    public static final int LEVEL_MILSPEC = 3;

    public int caseImageId;

    public WeaponAdapter adapter;

    private UniqueWeapon uniqueWeapon;

    private List<Weapon> weapons;

    private SoundPool soundPool;

    private SparseArray<Integer> soundMap = new SparseArray();

    private MediaPlayer player;

    private Random random = new Random();

    public SimulatorActivityBinding binding;

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
        binding = DataBindingUtil.setContentView(this,R.layout.simulator_activity);
        binding.simulatorTitle.rightButton.setVisibility(View.VISIBLE);
        binding.selectBar.setMinimumWidth(1);

        //初始化SoundPool和MediaPlayer
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 5);
        }
        soundMap.put(1,soundPool.load(this,R.raw.button,1));
        soundMap.put(2,soundPool.load(this,R.raw.display,1));
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
        layoutManager.setSpeed(0.5f);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recyclerView2.setLayoutManager(layoutManager);
        int spacingInPixels = DensityUtil.dip2px(10f);//设置item间隔
        binding.recyclerView2.addItemDecoration(new WeaponItemDecoration(spacingInPixels));
        View header = LayoutInflater.from(this).inflate(R.layout.item_header,binding.recyclerView2,false);//添加表头
        adapter.setHeaderView(header);

        binding.recyclerView2.setAdapter(adapter);
        binding.recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        soundPool.play(soundMap.get(2),1,1,5,0,1);
                        binding.uniqueWeaponLayout.setVisibility(View.VISIBLE);
                        //binding.uniqueItem.exteriorText.setVisibility(View.VISIBLE);
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
            if (weapon.getQuality() == LEVEL_CONVERT) {
                convertList.add(weapon);
            } else if (weapon.getQuality() == LEVEL_CLASSIFIED) {
                classifiedList.add(weapon);
            } else if (weapon.getQuality() == LEVEL_RESTRICTED) {
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
            else {
                weapon.setStatTrak(false);
            }
            weaponList.add(weapon);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.discard_button:
                binding.startButton.setClickable(true);
                binding.uniqueWeaponLayout.setVisibility(View.GONE);
                binding.drawerLayout2.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.keep_button:
                uniqueWeapon.save();
                binding.startButton.setClickable(true);
                binding.uniqueWeaponLayout.setVisibility(View.GONE);
                binding.drawerLayout2.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.left_button:
                finish();break;
            case R.id.right_button:
                Intent intent = new Intent(this,InventoryActivity.class);
                startActivity(intent);
                break;
            case R.id.start_button:
                soundPool.play(soundMap.get(1),1,1,5,0,1);
                player.start();
                binding.startButton.setClickable(false);
                binding.drawerLayout2.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                binding.recyclerView2.smoothScrollToPosition(38);
                final Observable<UniqueWeapon> observable = Observable.create(new ObservableOnSubscribe<UniqueWeapon>() {
                    @Override
                    public void subscribe(ObservableEmitter<UniqueWeapon> e) throws Exception {
                        setUniqueWeapon();
                        uniqueWeapon = new UniqueWeapon(weaponList.get(37));//根据武器基本类型生成具体的独有武器
                        e.onNext(uniqueWeapon);
                    }
                });
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<UniqueWeapon>() {
                            @Override
                            public void accept(UniqueWeapon uniqueWeapon) throws Exception {
                                binding.uniqueItem.weaponName.setText(uniqueWeapon.getWeaponName());
                                binding.uniqueItem.skinName.setText(uniqueWeapon.getSkinName());
                                binding.uniqueItem.exteriorText.setText(uniqueWeapon.getExterior());
                                Glide.with(SimulatorActivity.this).load(uniqueWeapon.getImageId()).dontAnimate().into(binding.uniqueItem.weaponImage);
                                if (uniqueWeapon.isStatTrak()){
                                    binding.uniqueItem.stImg.setVisibility(View.VISIBLE);
                                }else {
                                    binding.uniqueItem.stImg.setVisibility(View.GONE);
                                }
                                switch (uniqueWeapon.getQuality()){
                                    case LEVEL_MILSPEC:
                                        binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.milspec));
                                        break;
                                    case LEVEL_RESTRICTED:
                                        binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.restricted));
                                        break;
                                    case LEVEL_CLASSIFIED:
                                        binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.classified));
                                        break;
                                    case LEVEL_CONVERT:
                                        binding.uniqueItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.convert));
                                        break;
                                }
                            }
                        });
                break;
            default:break;
        }
    }

    /**计算得到的最终物品类型并替换列表中的相应位置**/
    private void setUniqueWeapon(){
        //计算最终得到的物品
        int degree = random.nextInt(500);
        if (degree == 499){
            Weapon rareWeapon = new Weapon();
            rareWeapon.setStatTrak(false);
            rareWeapon.setWeaponName("*Rare Special Item*");
            rareWeapon.setImageId(R.drawable.rare_special);
            rareWeapon.setSkinName(null);
            rareWeapon.setQuality(LEVEL_RARE);
            weaponList.set(37,rareWeapon);
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
    protected void onDestroy() {
        super.onDestroy();
        if (player != null){
            player.stop();
            player.release();
        }
    }
}
