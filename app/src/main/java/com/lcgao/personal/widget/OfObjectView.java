package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lcgao.personal.util.DpPixelUtil;

/**
 * Created by lcgao on 2018/3/2.
 */

public class OfObjectView extends View {

    public static final float RADIUS = DpPixelUtil.dpToPixel(20);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    PointF position = new PointF();
    public OfObjectView(Context context) {
        super(context);
    }

    public OfObjectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OfObjectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setColor(Color.parseColor("#009688"));
    }

    public PointF getPosition(){
        return position;
    }

    public void setPosition(PointF position){
        if(position == null){
            return;
        }
        this.position = position;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float innerPaddingLeft = RADIUS * 1;
        float innerPaddingRight = RADIUS * 1;
        float innerPaddingTop = RADIUS * 1;
        float innerPaddingBottom = RADIUS * 3;
        float width = getWidth() - innerPaddingLeft - innerPaddingRight - RADIUS * 2;
        float height = getHeight() - innerPaddingTop - innerPaddingBottom - RADIUS * 2;

        canvas.drawCircle(innerPaddingLeft + RADIUS + width * position.x, innerPaddingTop + RADIUS + height * position.y, RADIUS, paint);
    }
}
