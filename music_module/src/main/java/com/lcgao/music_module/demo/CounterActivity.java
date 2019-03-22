package com.lcgao.music_module.demo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcgao.music_module.R;
import com.lcgao.music_module.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CounterActivity extends AppCompatActivity {

    @BindView(R.id.tv_act_counter_count)
    TextView textView;

    @BindView(R.id.btn_act_counter_start_count)
    Button btnStart;

    @BindView(R.id.btn_act_counter_stop_count)
    Button btnStop;

    private CounterService counterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        ViewParent viewParent = ((LinearLayout)findViewById(R.id.ll_act_counter_main)).getParent();
        LogUtil.d("the parent of mainLayout is " + viewParent);
        ButterKnife.bind(this);
        Intent intent = new Intent(CounterActivity.this, CounterService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter counterActionFilter = new IntentFilter(CounterService.BROADCAST_COUNTER_ACTION);
        registerReceiver(counterActionReceiver, counterActionFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(counterActionReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            counterService = ((CounterService.MyBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            counterService = null;
        }
    };

    private BroadcastReceiver counterActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getIntExtra(CounterService.COUNTER_VALUE, 0);
            String text = String.valueOf(counter);
            textView.setText(text);
        }
    };

    @OnClick(R.id.btn_act_counter_start_count)
    public void onClickStartCount(){
        if(counterService != null){
            counterService.startCount();
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        }else {
            btnStart.setEnabled(false);
            btnStop.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_act_counter_stop_count)
    public void onClickStopCount(){
        if(counterService != null){
            counterService.stopCount();
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        }else {
            btnStart.setEnabled(false);
            btnStop.setEnabled(false);
        }
    }
}
