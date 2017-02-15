package com.zerlings.gabeisfaker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.zerlings.gabeisfaker.R;

/**
 * Created by 令子 on 2017/2/15.
 */

public class SimulatorActivity extends AppCompatActivity {

    public static final String CASE_NAME = "case_name";

    public static final String CASE_IMAGE_ID = "case_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.simulator_activity);
    }
}
