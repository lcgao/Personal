package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lcgao.personal.R;
import com.lcgao.personal.util.LogUtil;

/**
 * Created by lcgao on 2018/2/28.
 */

public class DrawPieChartView extends View {
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private RectF rectF1;
    private RectF rectF2;
    private Path path1;

    public DrawPieChartView(Context context) {
        super(context);
        initPaint();

    }

    public DrawPieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public DrawPieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    private void initPaint() {
        paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setAntiAlias(true);
        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(4);
        rectF1 = new RectF(200, 100, 600, 500);
        rectF2 = new RectF(210, 110, 610, 510);
        path1 = new Path();

        paint3 = new Paint();
        paint3.setTextSize(28);
        paint3.setColor(Color.BLACK);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint1.setColor(Color.RED);
        float startX1 = Float.parseFloat((400 - 200 * Math.cos(135 / 2 * 2 * Math.PI / 360)) + "");
        float startY1 = Float.parseFloat((300 - 200 * Math.sin(135 / 2 * 2 * Math.PI / 360)) + "");

        float stopX1 = Float.parseFloat((startX1 - 40 * Math.cos(135 / 2 * 2 * Math.PI / 360)) + "");
        float stopY1 = Float.parseFloat((startY1 - 40 * Math.sin(135 / 2 * 2 * Math.PI / 360)) + "");

        float stopX2 = stopX1 - 50;
        float stopY2 = stopY1;

        canvas.drawLine(startX1, startY1, stopX1, stopY1, paint2);
        canvas.drawLine(stopX1, stopY1, stopX2, stopY2, paint2);
        canvas.drawText("Lollipop", stopX2 - 120, stopY2, paint3);
        canvas.drawArc(rectF1, 180, 135, true, paint1); // 绘制扇形

        paint1.setColor(Color.YELLOW);
        canvas.drawArc(rectF2, -45, 43, true, paint1);
        paint1.setColor(getResources().getColor(R.color.purple));
        canvas.drawArc(rectF2, 0, 8, true, paint1);
        paint1.setColor(getResources().getColor(R.color.gray));
        canvas.drawArc(rectF2, 10, 8, true, paint1);
        paint1.setColor(getResources().getColor(R.color.teal));
        canvas.drawArc(rectF2, 20, 50, true, paint1);
        paint1.setColor(getResources().getColor(R.color.themecolor));
        canvas.drawArc(rectF2, 72, 108, true, paint1);
    }
}
