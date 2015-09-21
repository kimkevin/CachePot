package com.kimkevim.datacache.sample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.kimkevim.datacache.DataCache;
import com.kimkevim.datacache.R;
import com.kimkevim.datacache.sample.model.User;
import com.kimkevim.datacache.sample.util.NaviUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User userItem = new User(1, "KimKevin");

        /**
         * Put your Obejct to DataCache
         */
        DataCache.getInstance().put(userItem);

        NaviUtil.initFragment(this, new MainFragment());
    }
}
