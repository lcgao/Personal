package com.lcgao.personal.widget;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.lcgao.personal.R;

/**
 * Created by lcgao on 2018/3/1.
 */

public class ObjectAnimatorLayout extends RelativeLayout {
    ObjectAnimatorView view;
    Button btnAnimate;
    public ObjectAnimatorLayout(Context context) {
        super(context);
    }

    public ObjectAnimatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObjectAnimatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        view = (ObjectAnimatorView) findViewById(R.id.objectAnimatorView);
        btnAnimate = (Button) findViewById(R.id.btn_animate);

        btnAnimate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Keyframe keyframe1 = Keyframe.ofFloat(0f, 0);
                Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);
                Keyframe keyframe3 = Keyframe.ofFloat(1, 80);
                PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);
//                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "progress", 0, 100);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder);
                animator.setDuration(2000);
                animator.setInterpolator(new FastOutSlowInInterpolator());
                animator.start();
            }
        });

    }
}
