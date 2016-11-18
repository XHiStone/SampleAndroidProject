package com.app.sampleandroidproject.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.facebook.drawee.drawable.DrawableUtils;



/**
 * trunk
 * com.iss.innoz.ui.views.fresco
 *
 * @Author: xie
 * @Time: 2016/11/4 14:04
 * @Description:
 */


public class DrawableProgressBar extends Drawable {
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final int mWidth;
    private final int mHeight;
    private final Paint paint;
    private int mBackgroundColor = 0x00000000;
    private int mTextColor = 0xFFFF35A9;
    private int mColor = 0x1a000000;
    private int mPadding = 10;
    private int mBarWidth = 20;
    private int mLevel = 0;
    private boolean mHideWhenZero = false;
    private boolean isShow = false;

    public DrawableProgressBar(int width, int height) {
        mWidth = width;
        mHeight = height;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setColor(mTextColor);
        paint.setTextSize(40);
    }

    public void setShowText(boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * Gets the progress bar color.
     */
    public int getColor() {
        return mColor;
    }

    /**
     * Sets the progress bar color.
     */
    public void setColor(int color) {
        if (mColor != color) {
            mColor = color;
            invalidateSelf();
        }
    }

    /**
     * Gets the progress bar background color.
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Sets the progress bar background color.
     */
    public void setBackgroundColor(int backgroundColor) {
        if (mBackgroundColor != backgroundColor) {
            mBackgroundColor = backgroundColor;
            invalidateSelf();
        }
    }

    /**
     * Sets the progress bar padding.
     */
    public void setPadding(int padding) {
        if (mPadding != padding) {
            mPadding = padding;
            invalidateSelf();
        }
    }

    /**
     * Gets the progress bar padding.
     */
    @Override
    public boolean getPadding(Rect padding) {
        padding.set(mPadding, mPadding, mPadding, mPadding);
        return mPadding != 0;
    }

    /**
     * Gets the progress bar width.
     */
    public int getBarWidth() {
        return mBarWidth;
    }

    /**
     * Sets the progress bar width.
     */
    public void setBarWidth(int barWidth) {
        if (mBarWidth != barWidth) {
            mBarWidth = barWidth;
            invalidateSelf();
        }
    }

    /**
     * Gets whether the progress bar should be hidden when the progress is 0.
     */
    public boolean getHideWhenZero() {
        return mHideWhenZero;
    }

    /**
     * Sets whether the progress bar should be hidden when the progress is 0.
     */
    public void setHideWhenZero(boolean hideWhenZero) {
        mHideWhenZero = hideWhenZero;
    }

    @Override
    protected boolean onLevelChange(int level) {
        mLevel = level;
        invalidateSelf();
        return true;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(mPaint.getColor());
    }

    @Override
    public void draw(Canvas canvas) {
        if (mHideWhenZero && mLevel == 0) {
            return;
        }
        if (isShow) {
            canvas.drawText(mLevel / 100 + "%", mWidth / 2 - 20, mHeight / 2 - 20, paint);
        }
        drawBar(canvas, 10000, mBackgroundColor);
        drawBar(canvas, mLevel, mColor);
    }

    private void drawBar(Canvas canvas, int level, int color) {
        Rect bounds = getBounds();
        int length = mHeight * level / 10000;
        int xpos = bounds.left;
        int ypos = bounds.top;
        mPaint.setColor(color);

        canvas.drawRect(xpos, ypos + length, xpos + mWidth, ypos + mHeight, mPaint);
    }
}
