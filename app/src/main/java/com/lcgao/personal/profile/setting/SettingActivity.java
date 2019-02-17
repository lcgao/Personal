package com.lcgao.personal.profile.setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.util.DataCleanUtil;
import com.lcgao.personal.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_act_setting_cache_size)
    TextView tvCacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(SettingActivity.this);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        showCacheSize();
    }



    @OnClick(R.id.layout_act_setting_cache)
    public void onClickCleanCache(){
        if(tvCacheSize.getText().equals("0B")){
            ToastUtil.s("已清除");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataCleanUtil.clearAllCache(SettingActivity.this);
                ToastUtil.s("清除成功");
                showCacheSize();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setMessage("确认清除本地缓存吗？");
        dialog.show();
    }

    private void showCacheSize() {
        String cacheSize;
        try {
            cacheSize = DataCleanUtil.getTotalCacheSize(SettingActivity.this);
            if(cacheSize.equals("OK")){
                tvCacheSize.setText("0B");
            }else {
                tvCacheSize.setText(cacheSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
