package com.app.sampleandroidproject.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.utils.DensityUtils;


/**
 * Created by IntelliJ IDEA in maimai_a
 * cn.maitian.app.ui.view
 *
 * @Author: Yang Jianbo
 * @Time: 2016/7/4 0004 15:52
 * @Description: 自定义带删除按钮的EditText
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable;
    private boolean hasFoucs;

    private void init () {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.drawable.selector_clear);
        }

        mClearDrawable.setBounds(0, 0, DensityUtils.dp2px(getContext(), 17), DensityUtils.dp2px(getContext(), 17));
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }
    // ----------------------------------------------------

    protected void setClearIconVisible (boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
        setCompoundDrawablePadding(DensityUtils.dip2px(getContext(), 10));
    }

    // ----------------------------------------------------

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height) / 2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onTextChanged (CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged (CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged (Editable s) {

    }

    @Override
    public void onFocusChange (View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }
}
