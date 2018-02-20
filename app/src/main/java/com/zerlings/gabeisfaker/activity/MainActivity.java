package com.zerlings.gabeisfaker.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.zerlings.gabeisfaker.R;
import com.zerlings.gabeisfaker.db.AppDatabase;
import com.zerlings.gabeisfaker.db.Glove;
import com.zerlings.gabeisfaker.db.Knife;
import com.zerlings.gabeisfaker.utils.InitUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //判断数据库是否完整，不完整则重新录入数据
        long count = SQLite.selectCountOf().from(Knife.class).count();
        if (count != 220){
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
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_inventory:
                        Intent intent = new Intent(MainActivity.this,InventoryActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_quit:
                        finish();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,R.string.more_features,Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
