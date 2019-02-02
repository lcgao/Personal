package com.lcgao.music_module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcgao.common_library.util.RouterUtil;

@Route(path = RouterUtil.MODULE_MUSIC_MAIN_ACTIVITY_URL)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_activity_main);
    }
}
