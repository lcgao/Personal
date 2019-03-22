package com.lcgao.music_module;

import android.content.Context;
import android.widget.TextView;

public class LeakCanaryTest {
    private static LeakCanaryTest INSTANCE = null;
    private Context context;
    private TextView mTv;

    public static LeakCanaryTest getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new LeakCanaryTest(context);
        }
        return INSTANCE;
    }

    private LeakCanaryTest(Context context){
        this.context = context;
    }

    public void setText(TextView tv){
        mTv = tv;
        mTv.setText(context.getString(android.R.string.ok));
    }

    public void removeTextView(){
        mTv = null;
    }
}
