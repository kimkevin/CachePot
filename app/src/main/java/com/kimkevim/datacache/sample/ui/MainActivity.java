package com.kimkevim.datacache.sample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.kimkevim.datacache.R;
import com.kimkevim.datacache.sample.util.NaviUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        NaviUtil.initFragment(this, new JarFragment());
    }
}
