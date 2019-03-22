package com.lcgao.music_module.music.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.common_library.util.RuntimePermissionUtil;
import com.lcgao.music_module.LeakCanaryTest;
import com.lcgao.music_module.R;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.util.LocalMusicHelper;
import com.lcgao.music_module.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanLocalMusicActivity extends BaseActivity {
    private static final String TAG = "ScanLocalMusicActivity: ";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_local_music);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        toolbar.setTitle("扫描音乐");
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_act_scan_local_music_scan_all)
    public void onClickScanAll() {
//        if(!RuntimePermissionUtil.getStoragePermission(this)){
//            return;
//        }
//        List<Music> musicList = new ArrayList<>();
//        LocalMusicHelper.scanLocalMusic(this, musicList);
//        LogUtil.d(TAG + musicList);
        Button btn = findViewById(R.id.btn_act_scan_local_music_scan_all);
//        LeakCanaryTest.getInstance(this).setText(btn);  // 内存泄漏示例！！
        LeakCanaryTest.getInstance(this.getApplicationContext()).setText(btn);  // 内存泄漏示例！！

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            LogUtil.d("获取 " + permissions[i] + " 权限结果：" + grantResults[i]);
            if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                onClickScanAll();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LeakCanaryTest.getInstance(this.getApplicationContext()).removeTextView();
    }
}
