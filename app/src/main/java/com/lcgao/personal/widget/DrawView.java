package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by lcgao on 2018/3/1.
 */

public class DrawView extends AppCompatImageView {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    RectF bounds = new RectF();

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(60);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        afterOnDrawForeground(canvas);
        super.onDrawForeground(canvas);
    }

    private void afterOnDrawForeground(Canvas canvas){
        paint.setColor(Color.parseColor("#f44336"));

        canvas.drawRect(0, 40, 200, 120, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("New", 20, 100, paint);
    }

    //    @Override
//    protected void onDraw(Canvas canvas) {
//        beforeOnDraw(canvas);
//        super.onDraw(canvas);
//
//    }

    private void beforeOnDraw(Canvas canvas){
//        Layout layout = getLayout();
//        bounds.left = layout.getLineLeft(1);
//        bounds.right = layout.getLineRight(1);
//        bounds.top = layout.getLineTop(1);
//        bounds.bottom = layout.getLineBottom(1);
//        canvas.drawRect(bounds, paint);
//
//        bounds.left = layout.getLineLeft(layout.getLineCount() - 4);
//        bounds.right = layout.getLineRight(layout.getLineCount() - 4);
//        bounds.top = layout.getLineTop(layout.getLineCount() - 4);
//        bounds.bottom = layout.getLineBottom(layout.getLineCount() - 4);
//        canvas.drawRect(bounds, paint);
    }

    private void afterOnDraw(Canvas canvas){
//        Drawable drawable = getDrawable();
//        if(drawable == null){
//            return;
//        }
//        canvas.save();
//        canvas.concat(getImageMatrix());
//        Rect bounds = drawable.getBounds();
//        canvas.drawText(getResources().getString(R.string.image_size, bounds.width(), bounds.height()), 20, 40, paint);
//        canvas.restore();
    }
}
