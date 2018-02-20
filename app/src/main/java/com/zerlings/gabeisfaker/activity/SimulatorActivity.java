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
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.databinding.SimulatorActivityBinding;
import com.zerlings.gabeisfaker.db.Glove;
import com.zerlings.gabeisfaker.db.Glove_Table;
import com.zerlings.gabeisfaker.db.Gun;
import com.zerlings.gabeisfaker.db.Knife;
import com.zerlings.gabeisfaker.db.Knife_Table;
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

    public static final String RARE_ITEM_TYPE = "rare_item_type";

    public static final String RARE_SKIN_TYPE = "rare_skin_type";

    public static final int LEVEL_RARE = 7;

    public static final int LEVEL_CONVERT = 6;

    public static final int LEVEL_CLASSIFIED = 5;

    public static final int LEVEL_RESTRICTED = 4;

    public static final int LEVEL_MILSPEC = 3;

    public List<String> rareItems ;

    public List<String> rareSkins ;

    public int caseImageId;

    public SimulatorActivityBinding binding;

    public WeaponAdapter adapter;

    private UniqueItem uniqueItem;

    private List<Gun> weapons;

    private SoundPool soundPool;

    private SparseIntArray soundMap = new SparseIntArray();

    private MediaPlayer player;

    private Random random = new Random();

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
        caseImageId = intent.getIntExtra(CASE_IMAGE_ID,0);
        binding.simulatorTitle.titleText.setText(intent.getStringExtra(CASE_NAME));
        rareItems = Arrays.asList(intent.getStringArrayExtra(RARE_ITEM_TYPE));
        rareSkins = Arrays.asList(intent.getStringArrayExtra(RARE_SKIN_TYPE));

        initWeapons();//初始化各项武器列表
        initList();//初始化游戏列表

        //设置recyclerview显示方式
        adapter = new WeaponAdapter(weaponList,BR.gun);
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
                        binding.uniqueItemLayout.setVisibility(View.VISIBLE);
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
        weapons = InitUtils.initGun(caseImageId);
        convertList.clear();
        classifiedList.clear();
        restrictedList.clear();
        milspecList.clear();
        for (int i = 0;i < weapons.size();i++) {
            Gun gun = weapons.get(i);
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
                binding.uniqueItemLayout.setVisibility(View.GONE);
                binding.drawerLayout2.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.keep_button:
                uniqueItem.insert();
                binding.startButton.setClickable(true);
                binding.uniqueItemLayout.setVisibility(View.GONE);
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
                final Observable<UniqueItem> observable = Observable.create(new ObservableOnSubscribe<UniqueItem>() {
                    @Override
                    public void subscribe(ObservableEmitter<UniqueItem> e) throws Exception {
                        setChosenWeapon();
                        setUniqueItem();
                        e.onNext(uniqueItem);
                    }
                });
                observable.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<UniqueItem>() {
                            @Override
                            public void accept(UniqueItem uniqueItem) throws Exception {
                                binding.getItem.itemName.setText(uniqueItem.getItemName());
                                binding.getItem.skinName.setText(uniqueItem.getSkinName());
                                //原版刀无磨损
                                if (uniqueItem.getSkinName().equals(getString(R.string.vanilla))){
                                    binding.getItem.exteriorText.setVisibility(View.GONE);
                                }else {
                                    binding.getItem.exteriorText.setVisibility(View.VISIBLE);
                                    binding.getItem.exteriorText.setText(uniqueItem.getExterior());
                                }
                                Glide.with(SimulatorActivity.this).load(uniqueItem.getImageId())
                                        .into(binding.getItem.itemImage);
                                if (uniqueItem.isStatTrak()){
                                    binding.getItem.stImg.setVisibility(View.VISIBLE);
                                }else {
                                    binding.getItem.stImg.setVisibility(View.GONE);
                                }
                                switch (uniqueItem.getQuality()){
                                    case LEVEL_MILSPEC:
                                        binding.getItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.milspec));
                                        break;
                                    case LEVEL_RESTRICTED:
                                        binding.getItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.restricted));
                                        break;
                                    case LEVEL_CLASSIFIED:
                                        binding.getItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.classified));
                                        break;
                                    case LEVEL_CONVERT:
                                        binding.getItem.qualityLayout.setBackgroundColor(ContextCompat.getColor(SimulatorActivity.this,R.color.convert));
                                        break;
                                }
                            }
                        });
                break;
            default:break;
        }
    }

    /**计算得到的最终物品类型并替换列表中的相应位置**/
    private void setChosenWeapon(){

        int degree = random.nextInt(5000);
        if (degree >= 0 && degree < 13){
            //极其稀有的物品
            Gun rareWeapon = new Gun();
            rareWeapon.setStatTrak(false);
            rareWeapon.setGunName("*Rare Special Item*");
            rareWeapon.setImageId(R.drawable.rare_special);
            rareWeapon.setSkinName(null);
            rareWeapon.setQuality(LEVEL_RARE);
            weaponList.set(37,rareWeapon);
        }else if (degree >= 13 && degree < 45){
            weaponList.set(37,convertList.get(random.nextInt(convertList.size())));
        }else if (degree >= 45 && degree < 205){
            weaponList.set(37,classifiedList.get(random.nextInt(classifiedList.size())));
        }else if (degree >= 205 && degree < 1004){
            weaponList.set(37,restrictedList.get(random.nextInt(restrictedList.size())));
        }else {
            weaponList.set(37,milspecList.get(random.nextInt(milspecList.size())));
        }
    }
    /**根据基本物品类型生成具体的独特物品**/
    private void setUniqueItem(){

        if (weaponList.get(37).getQuality() == LEVEL_RARE){
            //判断出手套还是刀，手套不能有计数器
            String knifeType = rareItems.get(random.nextInt(rareItems.size()));
            String skinType = rareSkins.get(random.nextInt(rareSkins.size()));
            if (caseImageId == R.drawable.glove_case || caseImageId == R.drawable.operation_hydra_case
                    || caseImageId == R.drawable.clutch_case){
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
        if(binding.uniqueItemLayout.getVisibility() == View.VISIBLE){
            onClick(binding.discardButton);
        }else{
            super.onBackPressed();
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
