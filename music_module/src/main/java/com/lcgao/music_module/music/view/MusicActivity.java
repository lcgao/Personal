package com.lcgao.music_module.music.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.music_module.R;
import com.lcgao.music_module.music.view_model.MusicViewModel;
import com.lcgao.music_module.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MusicActivity extends BaseActivity {
    private static final String TAG = "MusicActivity: ";

    Button mButton;

    private MusicViewModel mMusicVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mMusicVM = ViewModelProviders.of(this).get(MusicViewModel.class);
        subscribe();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("歌名");

        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(savedInstanceState != null){
            String test = savedInstanceState.getString("extra_test");
            LogUtil.d(TAG + "[onCreate] restore extra_test:" + test);
        }
//        setAllowScreenRotate(true);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MusicActivity.this,"点我干哈？",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void subscribe() {
        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                String newText = "post times: " + aLong;
                mButton.setText(newText);
                LogUtil.d(TAG + "Updating timer: " + aLong);
            }
        };
        mMusicVM.getElapsedTime().observe(this, elapsedTimeObserver);

    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtil.d(TAG + "onSaveInstanceState");
        outState.putString("extra_test", "test");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("extra_test");
        LogUtil.d(TAG + "[onRestoreInstanceState] restore extra_test:" + test);
    }

    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        private String mName = "AsyncTask";

        public MyAsyncTask(String name) {
            super();
            mName = name;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mName;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LogUtil.d(TAG +s+"execute finish at "+ sdf.format(new Date()));
        }
    }
}
