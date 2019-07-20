package com.lcgao.personal.ipc.socket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.util.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TCPClientActivity extends BaseActivity {
    private static final String TAG = "MessengerActivity: ";

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;

    private Messenger mService;

    private Socket mClientSocket;
    private PrintWriter mPrintWriter;

    @BindView(R.id.tv_reply)
    TextView textView;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.et_send_msg)
    EditText etSendMsg;

    @SuppressWarnings("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG: {
                    textView.setText(textView.getText() + (String) msg.obj);
                    break;
                }
                case MESSAGE_SOCKET_CONNECTED: {
                    btnSend.setEnabled(true);
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, TCPServerService.class);
        startService(intent);
        new Thread() {
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void onDestroy() {
        try {
            mClientSocket.shutdownInput();
            mClientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @OnClick(R.id.btn_send)
    public void onClickSend(View view) {
        final String msg = etSendMsg.getText().toString();
        if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
            mPrintWriter.println(msg);
            etSendMsg.setText("");
            @SuppressLint("SimpleDateFormat")
            String time = new SimpleDateFormat("(HH:mm:ss)").format(new Date(System.currentTimeMillis()));
            final String showedMessage = "self" + time + ":" + msg + "\n";
            textView.setText(textView.getText() + showedMessage);
        }
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                LogUtil.d("Connect server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                LogUtil.d("Connect tcp server failed, retry...");
            }
        }

        try {
            //receive message from server
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                LogUtil.d("receive:" + msg);
                if (msg != null) {
                    @SuppressLint("SimpleDateFormat")
                    String time = new SimpleDateFormat("(HH:mm:ss)").format(new Date(System.currentTimeMillis()));
                    final String showedMessage = "server" + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMessage).sendToTarget();
                }
            }

            LogUtil.d("quit...");
            if (mPrintWriter != null) {
                mPrintWriter.close();
            }
            if (br != null) {
                br.close();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
