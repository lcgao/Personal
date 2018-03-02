package com.lcgao.personal.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lcgao.personal.R;

/**
 * Created by lcgao on 2018/3/2.
 */

public class PropertyValuesHolderLayout extends RelativeLayout {
    View view;
    Button btn_animate;
    public PropertyValuesHolderLayout(Context context) {
        super(context);
    }

    public PropertyValuesHolderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PropertyValuesHolderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        view = findViewById(R.id.view);
        btn_animate = (Button) findViewById(R.id.btn_animate);

        btn_animate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setTranslationX(-200f);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", -200, 200);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "rotation", 0, 1080);
                animator3.setDuration(1000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animator1).before(animator2);
                animatorSet.playTogether(animator2, animator3);

                animatorSet.start();

            }
        });
    }
}
