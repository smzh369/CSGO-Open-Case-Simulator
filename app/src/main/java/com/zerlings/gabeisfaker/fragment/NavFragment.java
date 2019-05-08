package com.zerlings.gabeisfaker.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.didikee.donate.AlipayDonate;
import android.didikee.donate.WeiXinDonate;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.activity.ActivityCollector;
import com.zerlings.gabeisfaker.activity.InventoryActivity;
import com.zerlings.gabeisfaker.activity.MainActivity;
import com.zerlings.gabeisfaker.activity.SimulatorActivity;
import com.zerlings.gabeisfaker.databinding.NavFragmentBinding;

import java.io.File;
import java.io.InputStream;

import io.reactivex.functions.Consumer;

import static com.zerlings.gabeisfaker.activity.MainActivity.snackbar;

public class NavFragment extends Fragment {

    public static final String payCode = "FKX04508GKMZBVRPRF3U8A";

    public static final String downloadUrl = " https://pan.baidu.com/s/1pMubID9";

    public static final String tradeUrl = "https://steamcommunity.com/tradeoffer/new/?partner=106454411&token=jSG5eL6U";

    private NavFragmentBinding binding;

    private FragmentActivity activity;

    private DrawerLayout drawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.nav_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        if (activity instanceof MainActivity){
            drawerLayout = ((MainActivity) activity).binding.mainDrawer;
        }else {
            drawerLayout = ((SimulatorActivity) activity).binding.simulatorDrawer;
        }
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_inventory:
                        Intent intent = new Intent(activity, InventoryActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_guide:
                        View guideView = LayoutInflater.from(activity).inflate(R.layout.instruction,null);
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
                        showPopupMenu(activity);
                        break;
                    case R.id.nav_quit:
                        ActivityCollector.finishAll();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**显示自定义PopupMenu**/
    @SuppressLint("RestrictedApi")
    private void showPopupMenu(final FragmentActivity activity ){

        /*创建PopupMenu*/
        Menu navMenu = binding.navView.getMenu();
        MenuItem menuItem = navMenu.findItem(R.id.nav_donate);
        PopupMenu donateMenu = new PopupMenu(activity, menuItem.getActionView());
        donateMenu.inflate(R.menu.donate_menu);
        /*自定义PopupMenu样式*/
        MenuPopupHelper popupHelper = new MenuPopupHelper(activity,(MenuBuilder) donateMenu.getMenu(),menuItem.getActionView());
        popupHelper.setForceShowIcon(true);
        /*设置监听器*/
        donateMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.alipay:
                        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(activity);
                        if(hasInstalledAlipayClient){
                            AlipayDonate.startAlipayClient(activity,payCode);
                        }else{
                            Toast.makeText(activity,R.string.alipay_text,Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.wechat:
                        RxPermissions permissions = new RxPermissions(activity);
                        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean){
                                            boolean hasInstalledWeCinClient = WeiXinDonate.hasInstalledWeiXinClient(activity);
                                            if (hasInstalledWeCinClient){
                                                snackbar = Snackbar.make(binding.navView,R.string.wechat_guide,Snackbar.LENGTH_INDEFINITE);
                                                snackbar.setAction("Go", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        InputStream weixinQrIs = getResources().openRawResource(R.raw.donate_wechat);
                                                        String qrPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                                                                + File.separator + "AndroidDonateSample" + File.separator + "donate_wechat.png";
                                                        WeiXinDonate.saveDonateQrImage2SDCard(qrPath, BitmapFactory.decodeStream(weixinQrIs));
                                                        WeiXinDonate.donateViaWeiXin(getActivity(), qrPath);
                                                    }
                                                }).show();
                                            }else{
                                                Toast.makeText(activity,R.string.wechat_text,Toast.LENGTH_SHORT).show();
                                            }
                                            drawerLayout.closeDrawers();
                                        }else{
                                            Toast.makeText(activity,R.string.wechat_permission,Toast.LENGTH_SHORT).show();
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
}
