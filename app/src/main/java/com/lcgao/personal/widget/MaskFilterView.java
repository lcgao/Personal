package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lcgao.personal.R;

/**
 * Created by lcgao on 2018/2/28.
 */

public class MaskFilterView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap1;
    Bitmap bitmap2;
    public MaskFilterView(Context context) {
        super(context);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.what_the_fuck);
//        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
//        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_OUT);
//        paint.setShader(shader);
        paint1.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        paint2.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
        paint3.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));
        paint4.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap1, 100, 50, paint1);
        canvas.drawBitmap(bitmap1, bitmap1.getWidth() + 200, 50, paint2);
        canvas.drawBitmap(bitmap1, 100, bitmap1.getHeight() + 100, paint3);
        canvas.drawBitmap(bitmap1, bitmap1.getWidth() + 200, bitmap1.getHeight() + 100, paint4);
//        canvas.drawCircle(300, 300, 300, paint);
    }
}
