package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lcgao.personal.R;
import com.lcgao.personal.util.LogUtil;

/**
 * Created by lcgao on 2018/2/27.
 */

public class DrawColorView extends View {
    public DrawColorView(Context context) {
        super(context);
        LogUtil.d("一个参数");

    }

    public DrawColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LogUtil.d("两个参数");

    }

    public DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.d("三个参数");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
    }
}
