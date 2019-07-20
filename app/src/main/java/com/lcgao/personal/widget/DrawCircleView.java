package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lcgao.personal.R;
import com.lcgao.personal.util.LogUtil;

/**
 * Created by lcgao on 2018/2/27.
 */

public class DrawCircleView extends View {
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Path path;
    public DrawCircleView(Context context) {
        super(context);
        LogUtil.d("一个参数");
        initPaint();
    }

    public DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LogUtil.d("两个参数");
        initPaint();

    }

    public DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.d("三个个参数");
        initPaint();

    }

    /**
     * 初始化Paint
     */
    private void initPaint() {
        paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setAntiAlias(true);

        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(4);
        paint2.setAntiAlias(true);


        paint3 = new Paint();
        paint3.setColor(getResources().getColor(R.color.themecolor));
        paint3.setStyle(Paint.Style.FILL);
        paint3.setAntiAlias(true);


        paint4 = new Paint();
        paint4.setColor(Color.BLACK);
        paint4.setStyle(Paint.Style.FILL);
        paint4.setAntiAlias(true);
//        paint4.setStrokeWidth(80);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300, 300, 200, paint1);

        canvas.drawCircle(800, 300, 200, paint2);

        canvas.drawCircle(300, 800, 200, paint3);

//        canvas.drawCircle(800, 800, 200, paint4);
        path.addCircle(800, 800, 200, Path.Direction.CW); // CW为顺时针
        path.addCircle(800, 800, 120, Path.Direction.CW); // CCW为逆时针
//        path.setFillType(Path.FillType.EVEN_ODD);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint4);

    }


}
