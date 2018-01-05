package com.lcgao.personal;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lcgao on 2017/7/27.
 */

public abstract class BaseActivity extends AppCompatActivity {


    //是否设置沉浸式状态栏
    private boolean isSetStatusBar = true;
    //是否允许全屏
    private boolean mAllowFullScreen = false;
    //是否允许旋转屏幕
    private boolean isAllowScreenRoate = false;
    //当前activity渲染的视图View
    private View mContextView = null;
    //是否输出日志信息
    private boolean isDebug;
    private String APP_NAME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            Bundle bundle = getIntent().getExtras();
            if(mAllowFullScreen){
                this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if(isSetStatusBar){
                steepStatusBar();
            }
            if(isAllowScreenRoate){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 沉浸式状态栏
     */
    private void steepStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 是否设置沉浸式状态栏
     * @param setStatusBar
     */
    public void setSetStatusBar(boolean setStatusBar) {
        isSetStatusBar = setStatusBar;
    }

    /**
     * 是否允许全屏
     * @param mAllowFullScreen
     */
    public void setmAllowFullScreen(boolean mAllowFullScreen) {
        this.mAllowFullScreen = mAllowFullScreen;
    }

    /**
     * 是否允许旋转屏幕
     * @param allowScreenRoate
     */
    public void setAllowScreenRoate(boolean allowScreenRoate) {
        isAllowScreenRoate = allowScreenRoate;
    }

    /**
     * 当前activity渲染的视图View
     * @param mContextView
     */
    public void setmContextView(View mContextView) {
        this.mContextView = mContextView;
    }

    /**
     * [初始化Bundle参数]
     *
     * @param paras
     */
    public abstract void initParas(Bundle paras);

    public abstract void initView();
}
