package com.lcgao.personal.favourite.music;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(MusicActivity.this);
        toolbar.setTitle("音乐");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
    }
}
