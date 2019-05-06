package com.zerlings.gabeisfaker.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.didikee.donate.AlipayDonate;
import android.didikee.donate.WeiXinDonate;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.support.v7.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.AppDatabase;
import com.zerlings.gabeisfaker.db.Glove;
import com.zerlings.gabeisfaker.db.Knife;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    public static final String payCode = "FKX04508GKMZBVRPRF3U8A";

    public static final String tradeUrl = "https://steamcommunity.com/tradeoffer/new/?partner=106454411&token=jSG5eL6U";

    public static final String downloadUrl = " https://pan.baidu.com/s/1pMubID9";

    public static Snackbar snackbar;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

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

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_inventory:
                        Intent intent = new Intent(MainActivity.this,InventoryActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_guide:
                        View guideView = LayoutInflater.from(MainActivity.this).inflate(R.layout.instruction,null);
                        PopupWindow guideWindow = new PopupWindow(guideView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        guideWindow.setFocusable(true);
                        guideWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        guideWindow.showAtLocation(drawerLayout, Gravity.CENTER,0,0);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_share:
                        Intent sharedIntent = new Intent();
                        sharedIntent.setAction(Intent.ACTION_SEND);
                        sharedIntent.putExtra(Intent.EXTRA_SUBJECT,R.string.shared_text);
                        sharedIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.shared_text) + downloadUrl);
                        sharedIntent.setType("text/plain");
                        startActivity(Intent.createChooser(sharedIntent,getString(R.string.shared_title)));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_donate:
                        showPopupMenu();
                        break;
                    case R.id.nav_quit:
                        ActivityCollector.finishAll();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    /**显示自定义PopupMenu**/
    @SuppressLint("RestrictedApi")
    private void showPopupMenu(){

        /*创建PopupMenu*/
        Menu navMenu = navigationView.getMenu();
        MenuItem menuItem = navMenu.findItem(R.id.nav_donate);
        PopupMenu donateMenu = new PopupMenu(MainActivity.this, menuItem.getActionView());
        donateMenu.inflate(R.menu.donate_menu);
        /*自定义PopupMenu样式*/
        MenuPopupHelper popupHelper = new MenuPopupHelper(MainActivity.this,(MenuBuilder) donateMenu.getMenu(),menuItem.getActionView());
        popupHelper.setForceShowIcon(true);
        /*设置监听器*/
        donateMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.alipay:
                        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(MainActivity.this);
                        if(hasInstalledAlipayClient){
                            AlipayDonate.startAlipayClient(MainActivity.this,payCode);
                        }else{
                            Toast.makeText(MainActivity.this,R.string.alipay_text,Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.wechat:
                        RxPermissions permissions = new RxPermissions(MainActivity.this);
                        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean){
                                            boolean hasInstalledWeCinClient = WeiXinDonate.hasInstalledWeiXinClient(MainActivity.this);
                                            if (hasInstalledWeCinClient){
                                            snackbar = Snackbar.make(navigationView,R.string.wechat_guide,Snackbar.LENGTH_INDEFINITE);
                                            snackbar.setAction("Go", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    InputStream weixinQrIs = getResources().openRawResource(R.raw.donate_wechat);
                                                    String qrPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                                                            + File.separator + "AndroidDonateSample" + File.separator + "donate_wechat.png";
                                                    WeiXinDonate.saveDonateQrImage2SDCard(qrPath, BitmapFactory.decodeStream(weixinQrIs));
                                                    WeiXinDonate.donateViaWeiXin(MainActivity.this, qrPath);
                                                }
                                            }).show();
                                            }else{
                                                Toast.makeText(MainActivity.this,R.string.wechat_text,Toast.LENGTH_SHORT).show();
                                            }
                                            drawerLayout.closeDrawers();
                                        }else{
                                            Toast.makeText(MainActivity.this,R.string.wechat_permission,Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        break;
                    case R.id.steam:
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri url = Uri.parse(tradeUrl);
                        intent.setData(url);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        /*显示PopupMenu*/
        popupHelper.show();
    }

    @Override
    public void onBackPressed() {
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }else if (drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawers();
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
