package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lcgao.personal.R;

/**
 * Created by lcgao on 2018/3/1.
 */

public class ClipView extends View {
//    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    Bitmap bitmap;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rotate(canvas);
    }

    private void rotate(Canvas canvas) {

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        canvas.save();
        canvas.rotate(180, point1.x + bitmapWidth / 2, point1.y + bitmapHeight / 2);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(45, point2.x + bitmapWidth / 2, point2.y + bitmapHeight / 2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();

    }

    private void clipPath(Canvas canvas) {
        //member
        Path path1 = new Path();
        Path path2 = new Path();


        //code block
        path1.addCircle(point1.x + 100, point1.y + 100, 150, Path.Direction.CW);
        path2.setFillType(Path.FillType.INVERSE_WINDING);
        path2.addCircle(point2.x + 200, point2.y + 200, 150, Path.Direction.CW);
        //core
        canvas.save();
        canvas.clipPath(path1);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.clipPath(path2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }

    private void clipRect(Canvas canvas) {
        int left = (getWidth() - bitmap.getWidth()) / 2;
        int top = (getHeight() - bitmap.getHeight()) / 2;
        canvas.save();
        canvas.clipRect(left + 50, top + 50, left + 300, top + 300);
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();
    }
}
