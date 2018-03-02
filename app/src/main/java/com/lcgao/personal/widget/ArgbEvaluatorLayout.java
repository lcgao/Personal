package com.lcgao.personal.widget;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lcgao.personal.R;

/**
 * Created by lcgao on 2018/3/2.
 */

public class ArgbEvaluatorLayout extends RelativeLayout {
    ArgbEvaluatorView view;
    Button btn_animate;
    public ArgbEvaluatorLayout(Context context) {
        super(context);
    }

    public ArgbEvaluatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArgbEvaluatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = (ArgbEvaluatorView) findViewById(R.id.view);
        btn_animate = (Button) findViewById(R.id.btn_animate);

        btn_animate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofInt(view, "color", 0xffff0000, 0xff00ff00);
                animator.setEvaluator(new HsvEvaluator());
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
    }

    private class HsvEvaluator implements TypeEvaluator<Integer> {

        float[] startHsv = new float[3];
        float[] endHsv = new float[3];
        float[] outHsv = new float[3];

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            Color.colorToHSV(startValue, startHsv);
            Color.colorToHSV(endValue, endHsv);

            if(endHsv[0] - startHsv[0] > 180){
                endHsv[0] -= 360;
            }else if (endHsv[0] - startHsv[0] < -180){
                endHsv[0] += 360;
            }

            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
            if(outHsv[0] > 360){
                outHsv[0] -= 360;
            }else if(outHsv[0] < 0){
                outHsv[0] += 360;
            }

            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

            int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);
            return Color.HSVToColor(alpha, outHsv);

//            // 把 ARGB 转换成 HSV
//            Color.colorToHSV(startValue, startHsv);
//            Color.colorToHSV(endValue, endHsv);
//
//            // 计算当前动画完成度（fraction）所对应的颜色值
//            if (endHsv[0] - startHsv[0] > 180) {
//                endHsv[0] -= 360;
//            } else if (endHsv[0] - startHsv[0] < -180) {
//                endHsv[0] += 360;
//            }
//            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
//            if (outHsv[0] > 360) {
//                outHsv[0] -= 360;
//            } else if (outHsv[0] < 0) {
//                outHsv[0] += 360;
//            }
//            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
//            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;
//
//            // 计算当前动画完成度（fraction）所对应的透明度
//            int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);
//
//            // 把 HSV 转换回 ARGB 返回
//            return Color.HSVToColor(alpha, outHsv);

        }
    }
}
