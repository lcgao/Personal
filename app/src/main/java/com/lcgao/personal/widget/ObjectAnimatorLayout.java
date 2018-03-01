package com.lcgao.personal.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

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
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "progress", 0, 100);
                animator.setDuration(1500);
                animator.setInterpolator(new FastOutSlowInInterpolator());
                animator.start();
            }
        });

    }
}
