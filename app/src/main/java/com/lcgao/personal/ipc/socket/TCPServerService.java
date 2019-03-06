package com.lcgao.personal.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lcgao.personal.util.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {
    private static final String TAG = "TCPServerService: ";

    private boolean mIsServiceDestroyed = false;
    private String[] mDefinedMessage = new String[]{
            "Hello, hhh",
            "What's your name?",
            "It's a good day in Beijing!Shy..",
            "You know what, that I can chat with many people in the same time.",
            "Let me talk a jok to you, look, is it true or false that a guy could have a good luck if he is glad to laugh."
    };

    @Override
    public void onCreate() {
        new Thread(new TCPServer()).start();
        super.onCreate();
        LogUtil.d(TAG + "onCreate()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d(TAG + "onBind()");

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG + "onStartCommand()");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG + "onDestroy()");

        mIsServiceDestroyed = true;
        stopSelf();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d(TAG + "onUnbind()");

        return super.onUnbind(intent);
    }

    private class TCPServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.err.println("establish tcp server failed, port:8688");
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestroyed) {
                //accept request from clients
                try {
                    final Socket client = serverSocket.accept();
                    LogUtil.d("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // for receiving message from client.
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // for sending message to client.
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("Welcome to the chat room!");
        while (!mIsServiceDestroyed) {
            String str = in.readLine();
            LogUtil.d("Message from client: " + str);
            if (str == null) {
                // Client disconnects
                break;
            }
            int i = new Random().nextInt(mDefinedMessage.length);
            String msg = mDefinedMessage[i];
            out.println(msg);
            LogUtil.d("send: " + msg);
        }
        LogUtil.d("client quit.");
        //close stream
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }

    }
}
