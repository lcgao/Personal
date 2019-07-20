package com.lcgao.personal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.lcgao.personal.R;


/**
 * Created by lcgao on 2018/1/26.
 */

public class EditTextClear extends AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher{
    private Drawable mClearTextIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;

    public EditTextClear(Context context) {
        super(context);
        init(context);
    }

    public EditTextClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditTextClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context){
        final Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_clear);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, context.getResources().getColor(R.color.themecolor));
        mClearTextIcon = wrappedDrawable;
        mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(),mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
    }

    private void setClearIconVisible(boolean clearIconVisible) {
        if(mClearTextIcon == null){
            return;
        }
        mClearTextIcon.setVisible(clearIconVisible, false);
        Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(compoundDrawables[0],
                compoundDrawables[1],
                clearIconVisible ? mClearTextIcon : null,
                compoundDrawables[3]);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(isFocusable()){
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            setClearIconVisible(getText().length() > 0);
        }else {
            setClearIconVisible(false);
        }
        if(mOnFocusChangeListener != null){
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int x = (int) event.getX();
        if(mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()){
           if(event.getAction() == MotionEvent.ACTION_UP) {
               setText("");
           }
           return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(v, event);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }
}
