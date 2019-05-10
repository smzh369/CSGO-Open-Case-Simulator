package com.zerlings.gabeisfaker.activity;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.databinding.SimulatorActivityBinding;
import com.zerlings.gabeisfaker.db.Glove;
import com.zerlings.gabeisfaker.db.Glove_Table;
import com.zerlings.gabeisfaker.db.Gun;
import com.zerlings.gabeisfaker.db.Knife;
import com.zerlings.gabeisfaker.db.Knife_Table;
import com.zerlings.gabeisfaker.db.Stats;
import com.zerlings.gabeisfaker.db.UniqueItem;
import com.zerlings.gabeisfaker.recyclerview.CustomLinearLayoutManager;
import com.zerlings.gabeisfaker.recyclerview.WeaponItemDecoration;
import com.zerlings.gabeisfaker.utils.DensityUtil;
import com.zerlings.gabeisfaker.recyclerview.WeaponAdapter;
import com.zerlings.gabeisfaker.BR;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by smzh369 on 2017/2/15.
 */

public class SimulatorActivity extends BaseActivity implements View.OnClickListener{

    public static final String CASE_NAME = "case_name";

    public static final String CASE_IMAGE_NAME = "case_image_name";

    public static final String RARE_ITEM_TYPE = "rare_item_type";

    public static final String RARE_SKIN_TYPE = "rare_skin_type";

    public static final int LEVEL_RARE = 7;

    public static final int LEVEL_CONVERT = 6;

    public static final int LEVEL_CLASSIFIED = 5;

    public static final int LEVEL_RESTRICTED = 4;

    public static final int LEVEL_MILSPEC = 3;

    public List<String> rareItems ;

    public List<String> rareSkins ;

    public String caseImageName;

    public SimulatorActivityBinding binding;

    private UniqueItem uniqueItem;

    private List<Gun> weapons;

    private SoundPool soundPool;

    private SparseIntArray soundMap = new SparseIntArray();

    private MediaPlayer player;

    private Random random = new Random();

    private Stats stats = new Stats();

    private List<Gun> weaponList = new ArrayList<>(42);
    private List<Gun> convertList = new ArrayList<>();
    private List<Gun> classifiedList = new ArrayList<>();
    private List<Gun> restrictedList = new ArrayList<>();
    private List<Gun> milspecList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this,R.layout.simulator_activity);
        binding.simulatorTitle.rightButton.setVisibility(View.VISIBLE);
        binding.drawLayout.selectBar.setMinimumWidth(1);

        /*初始化统计数据*/
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        stats.setRareCount(preferences.getString("rare","0"));
        stats.setConvertCount(preferences.getString("convert","0"));
        stats.setClassifiedCount(preferences.getString("classified","0"));
        stats.setRestrictedCount(preferences.getString("restricted","0"));
        stats.setMilspecCount(preferences.getString("milspec","0"));
        stats.setTotalCount(preferences.getString("total","0"));
        binding.setStats(stats);

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
        caseImageName = intent.getStringExtra(CASE_IMAGE_NAME);
        binding.simulatorTitle.titleText.setText(intent.getStringExtra(CASE_NAME));
        rareItems = Arrays.asList(intent.getStringArrayExtra(RARE_ITEM_TYPE));
        rareSkins = Arrays.asList(intent.getStringArrayExtra(RARE_SKIN_TYPE));

        //设置布局动画
        LayoutTransition transition = new LayoutTransition();
        int windowHeight = getResources().getDisplayMetrics().heightPixels;
        Animator appearAnim = ObjectAnimator.ofFloat(null,"translationY",windowHeight,0);
        appearAnim.setDuration(transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING,appearAnim);
        binding.simulatorLayout.setLayoutTransition(transition);

        initWeapons();//初始化各项武器列表
        initList();//初始化游戏列表

        //设置recyclerview
        WeaponAdapter adapter = new WeaponAdapter(weaponList,BR.gun);
        final CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setSpeed(0.5f);//动画速度
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.drawLayout.simulatorList.setLayoutManager(layoutManager);
        int spacingInPixels = DensityUtil.dip2px(10f);//设置item间隔
        binding.drawLayout.simulatorList.addItemDecoration(new WeaponItemDecoration(spacingInPixels));
        View header = LayoutInflater.from(this).inflate(R.layout.item_header,binding.drawLayout.simulatorList,false);//添加表头
        adapter.setHeaderView(header);
        binding.drawLayout.simulatorList.setAdapter(adapter);

        /*滑动结束展示所得物品*/
        binding.drawLayout.simulatorList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        player.pause();
                        player.seekTo(0);
                        soundPool.play(soundMap.get(2),1,1,5,0,1);
                        binding.decideLayout.setVisibility(View.VISIBLE);
                        binding.setStats(stats);
                        break;
                    default:break;
                }
            }
        });

        /*切换模式*/
        binding.simulatorMode.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    layoutManager.setSpeed(0.1f);
                    binding.simulatorMode.animationSpeed.setText(R.string.fast);
                }else{
                    layoutManager.setSpeed(0.5f);
                    binding.simulatorMode.animationSpeed.setText(R.string.normal);
                }
            }
        });

        binding.simulatorTitle.leftButton.setOnClickListener(this);
        binding.simulatorTitle.rightButton.setOnClickListener(this);
        binding.startButton.setOnClickListener(this);
        binding.backButton.setOnClickListener(this);
        binding.discardButton.setOnClickListener(this);
        binding.keepButton.setOnClickListener(this);

    }

    /**初始化各类武器列表**/
    public void initWeapons(){
        weapons = InitUtils.initGun(caseImageName);
        convertList.clear();
        classifiedList.clear();
        restrictedList.clear();
        milspecList.clear();
        for (Gun gun : weapons) {
            if (gun.getQuality() == LEVEL_CONVERT) {
                convertList.add(gun);
            } else if (gun.getQuality() == LEVEL_CLASSIFIED) {
                classifiedList.add(gun);
            } else if (gun.getQuality() == LEVEL_RESTRICTED) {
                restrictedList.add(gun);
            } else {
                milspecList.add(gun);
            }
        }
    }

    public void initList(){

        weaponList.clear();
        for (int i = 0;i<40;i++){
            int index = random.nextInt(weapons.size());
            int st = random.nextInt(10);
            Gun gun = weapons.get(index);
            if (st == 0){
                gun.setStatTrak(true);
            }
            else {
                gun.setStatTrak(false);
            }
            weaponList.add(gun);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.discard_button:
                binding.startButton.setClickable(true);
                binding.simulatorDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                //开箱结束后恢复侧滑菜单及标题栏左右按钮
                binding.simulatorTitle.leftButton.setClickable(true);
                binding.simulatorTitle.rightButton.setClickable(true);
                binding.backButton.setClickable(true);
                initList();
                binding.drawLayout.simulatorList.scrollToPosition(0);
                binding.decideLayout.setVisibility(View.GONE);
                break;
            case R.id.keep_button:
                uniqueItem.insert();
                binding.startButton.setClickable(true);
                binding.simulatorDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                binding.simulatorTitle.leftButton.setClickable(true);
                binding.simulatorTitle.rightButton.setClickable(true);
                binding.backButton.setClickable(true);
                initList();
                binding.drawLayout.simulatorList.scrollToPosition(0);
                binding.decideLayout.setVisibility(View.GONE);
                break;
            case R.id.left_button:
                binding.simulatorDrawer.openDrawer(GravityCompat.START);
                break;
            case R.id.back_button:
                finish();
                break;
            case R.id.right_button:
                Intent intent = new Intent(this,InventoryActivity.class);
                startActivity(intent);
                break;
            case R.id.start_button:
                soundPool.play(soundMap.get(1),1,1,5,0,1);
                player.start();
                binding.startButton.setClickable(false);
                //一次开箱结束前锁定侧滑菜单并使标题栏左右按钮失效
                binding.simulatorDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                binding.simulatorTitle.leftButton.setClickable(false);
                binding.simulatorTitle.rightButton.setClickable(false);
                binding.backButton.setClickable(false);
                //游戏开始
                binding.drawLayout.simulatorList.smoothScrollToPosition(38);
                Single<UniqueItem> single = Single.create(new SingleOnSubscribe<UniqueItem>() {
                    @Override
                    public void subscribe(SingleEmitter<UniqueItem> e) throws Exception {
                        setChosenWeapon();
                        setUniqueItem();
                        e.onSuccess(uniqueItem);
                    }
                });
                single.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<UniqueItem>() {
                            @Override
                            public void accept(UniqueItem uniqueItem) throws Exception {
                                binding.setUniqueItem(uniqueItem);
                                //原版刀无磨损
                                if (uniqueItem.getSkinName().equals(getString(R.string.vanilla))){
                                    binding.getItem.exteriorText.setVisibility(View.GONE);
                                }else {
                                    binding.getItem.exteriorText.setVisibility(View.VISIBLE);
                                    binding.getItem.exteriorText.setText(uniqueItem.getExterior());
                                }
                            }
                        });
                break;
            default:break;
        }
    }

    /**计算得到的最终物品类型并替换列表中的相应位置**/
    private void setChosenWeapon(){
        stats.setTotalCount(String.valueOf(Integer.parseInt(stats.getTotalCount()) + 1));
        int degree = random.nextInt(5000);
        if (degree >= 0 && degree < 13){
            //极其稀有的物品
            stats.setRareCount(String.valueOf(Integer.parseInt(stats.getRareCount()) + 1));
            Gun rareWeapon = new Gun();
            rareWeapon.setStatTrak(false);
            rareWeapon.setGunName("*Rare Special Item*");
            rareWeapon.setImageName("rare_special");
            rareWeapon.setSkinName(null);
            rareWeapon.setQuality(LEVEL_RARE);
            weaponList.set(37,rareWeapon);
        }else if (degree >= 13 && degree < 45){
            stats.setConvertCount(String.valueOf(Integer.parseInt(stats.getConvertCount()) + 1));
            weaponList.set(37,convertList.get(random.nextInt(convertList.size())));
        }else if (degree >= 45 && degree < 205){
            stats.setClassifiedCount(String.valueOf(Integer.parseInt(stats.getClassifiedCount()) + 1));
            weaponList.set(37,classifiedList.get(random.nextInt(classifiedList.size())));
        }else if (degree >= 205 && degree < 1004){
            stats.setRestrictedCount(String.valueOf(Integer.parseInt(stats.getRestrictedCount()) + 1));
            weaponList.set(37,restrictedList.get(random.nextInt(restrictedList.size())));
        }else {
            stats.setMilspecCount(String.valueOf(Integer.parseInt(stats.getMilspecCount()) + 1));
            weaponList.set(37,milspecList.get(random.nextInt(milspecList.size())));
        }
    }
    /**根据基本物品类型生成具体的独特物品**/
    private void setUniqueItem(){

        if (weaponList.get(37).getQuality() == LEVEL_RARE){
            //判断出手套还是刀，手套不能有计数器
            String knifeType = rareItems.get(random.nextInt(rareItems.size()));
            String skinType = rareSkins.get(random.nextInt(rareSkins.size()));
            if (caseImageName == "glove_case" || caseImageName == "operation_hydra_case"
                    || caseImageName == "clutch_case"){
                Glove glove = SQLite.select().from(Glove.class)
                        .where(Glove_Table.skinName.eq(skinType))
                        .querySingle();
                uniqueItem = new UniqueItem(glove);
            }else {
                Knife knife = SQLite.select().from(Knife.class)
                        .where(Knife_Table.knifeName.eq(knifeType),Knife_Table.skinName.eq(skinType))
                        .querySingle();
                uniqueItem = new UniqueItem(knife);
            }
        }else {
            uniqueItem = new UniqueItem(weaponList.get(37));
        }

    }

    @Override
    public void onBackPressed() {
        if(binding.decideLayout.getVisibility() == View.VISIBLE){
            onClick(binding.discardButton);
        }else if (binding.simulatorDrawer.isDrawerOpen(Gravity.START)){
            binding.simulatorDrawer.closeDrawers();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (player != null){
            player.stop();
            player.release();
        }
        if (soundPool != null){
            soundPool.release();
        }

        /*暂存统计数据*/
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("rare",stats.getRareCount());
        editor.putString("convert",stats.getConvertCount());
        editor.putString("classified",stats.getClassifiedCount());
        editor.putString("restricted",stats.getRestrictedCount());
        editor.putString("milspec",stats.getMilspecCount());
        editor.putString("total",stats.getTotalCount());
        editor.apply();

        super.onDestroy();
    }
}
