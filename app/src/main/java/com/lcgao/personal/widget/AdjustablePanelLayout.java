package com.lcgao.personal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.lcgao.personal.R;
import com.lcgao.personal.util.DpPixelUtil;

/**
 * Created by lcgao on 2018/3/4.
 */

public class AdjustablePanelLayout extends RelativeLayout {
    FrameLayout parentLayout;
    AppCompatSeekBar heightBar;
    AppCompatSeekBar widthBar;
    float bottomMargin = DpPixelUtil.dpToPixel(48);
    float minWidth = DpPixelUtil.dpToPixel(80);
    float minHeight = DpPixelUtil.dpToPixel(100);

    public AdjustablePanelLayout(Context context) {
        super(context);
    }

    public AdjustablePanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustablePanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        parentLayout = (FrameLayout) findViewById(R.id.parentLayout);
        widthBar = (AppCompatSeekBar) findViewById(R.id.widthBar);
        heightBar = (AppCompatSeekBar) findViewById(R.id.heightBar);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LayoutParams layoutParams = (LayoutParams) parentLayout.getLayoutParams();
                layoutParams.width = (int) (minWidth + (AdjustablePanelLayout.this.getWidth()
                        - minWidth) * widthBar.getProgress() / 100);
                layoutParams.height = (int) (minHeight + (AdjustablePanelLayout.this.getHeight()
                        - minHeight) * heightBar.getProgress() / 100);
                parentLayout.setLayoutParams(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        widthBar.setOnSeekBarChangeListener(listener);
        heightBar.setOnSeekBarChangeListener(listener);
    }
}
