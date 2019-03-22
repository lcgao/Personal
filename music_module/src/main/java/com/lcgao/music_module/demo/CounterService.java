package com.lcgao.music_module.demo;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import javax.xml.validation.Validator;

public class CounterService extends Service {

    private boolean isStop = true;

    public final static String BROADCAST_COUNTER_ACTION = "com.lcgao.music_module.broadcast.COUNTER_ACTION";
    public final static String COUNTER_VALUE = "com.lcgao.music_module.broadcast.counter.value";
    public class MyBinder extends Binder {
        public CounterService getService(){
            return CounterService.this;
        }
    }

    public CounterService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void startCount(){
        AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                Integer initCounter = integers[0];
                isStop = false;
                while(!isStop){
                    publishProgress(initCounter);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    initCounter ++;
                }
                return initCounter;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                int counter = values[0];
                Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
                intent.putExtra(COUNTER_VALUE, counter);
                sendBroadcast(intent);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                int counter = integer;
                Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
                intent.putExtra(COUNTER_VALUE, counter);
                sendBroadcast(intent);
            }
        };
        task.execute(0);
    }

    public void stopCount(){
        isStop = true;
    }

}
