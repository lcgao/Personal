package com.lcgao.personal.widget;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lcgao.personal.R;
import com.lcgao.personal.util.ToastUtil;

import java.util.Map;

/**
 * Created by lcgao on 2018/3/2.
 */

public class OfObjectLayout extends RelativeLayout{
    OfObjectView view;
    Button btn_animate;
    public OfObjectLayout(Context context) {
        super(context);
    }

    public OfObjectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OfObjectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        view = (OfObjectView) findViewById(R.id.view);
        btn_animate = (Button) findViewById(R.id.btn_animate);
        btn_animate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofObject(view, "position",
                        new PointFEvaluator(), new PointF(0, 0), new PointF(1, 1));
                animator.setInterpolator(new OvershootInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
    }

    private class PointFEvaluator implements TypeEvaluator<PointF> {
        PointF newPoint = new PointF();

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

            float x = startValue.x + (fraction * (endValue.x - startValue.x));
//            float y = startValue.y + (fraction * (endValue.y - startValue.y));
//            float y = (float)Math.sqrt(Double.parseDouble(x + ""));
            float y = (float)Math.sqrt(Double.parseDouble(x + ""));

            newPoint.set(x, y);

            return newPoint;
        }
    }
}
