package com.lcgao.personal.ipc.socket;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.ipc.messenger.MessengerService;
import com.lcgao.personal.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TCPClientActivity extends BaseActivity {
    private static final String TAG = "MessengerActivity: ";

    private Messenger mService;

    @BindView(R.id.tv_reply)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, MessengerService.class);

    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            }
        }
    }
}
