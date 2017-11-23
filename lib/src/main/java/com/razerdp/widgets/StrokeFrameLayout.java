package com.razerdp.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by 大灯泡 on 2017/11/23.
 * <p>
 * 描边的FrameLayout
 */

public class StrokeFrameLayout extends FrameLayout implements IStrokeLayouts {
    private int excludeSide = EXCLUDE_NONE;
    private int strokeWidth = 2;
    private int strokeColor = 0xFFCCCCCC;
    private Paint strokePaint;
    private Path mPath;
    private Rect mDrawRect;

    public StrokeFrameLayout(Context context) {
        super(context);
        initAttrs(null);
    }

    public StrokeFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public StrokeFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        setWillNotDraw(false);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StrokeFrameLayout);
            excludeSide = a.getInt(R.styleable.StrokeFrameLayout_exclude_side, EXCLUDE_NONE);
            strokeWidth = a.getDimensionPixelSize(R.styleable.StrokeFrameLayout_stroke_width, 2);
            strokeColor = a.getColor(R.styleable.StrokeFrameLayout_stroke_color, 0xFFCCCCCC);
            a.recycle();
        } else {
            excludeSide = EXCLUDE_NONE;
            strokeWidth = 2;
            strokeColor = 0xFFCCCCCC;
        }
        if (strokePaint == null) strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setColor(strokeColor);
        if (mPath == null) mPath = new Path();
        if (mDrawRect == null) mDrawRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (excludeSide != EXCLUDE_ALL) {
            canvas.getClipBounds(mDrawRect);
            mPath = PathHelper.calculatePath(this, mPath, mDrawRect, excludeSide);
            canvas.drawPath(mPath, strokePaint);
        }
        super.onDraw(canvas);
    }

    @Override
    public void setExcludeSide(int excludeSide) {
        this.excludeSide = excludeSide;
        postInvalidate();
    }

    @Override
    public int getExcludeSide() {
        return excludeSide;
    }

    @Override
    public void setStrokeWidth(float strokeWidth) {
        if (strokePaint != null) {
            strokePaint.setStrokeWidth(strokeWidth);
        }
        postInvalidate();
    }

    @Override
    public float getStrokeWidth() {
        return strokePaint == null ? 0 : strokePaint.getStrokeWidth();
    }

    @Override
    public void setStrokeColor(int strokeColor) {
        if (strokePaint != null) {
            strokePaint.setColor(strokeColor);
        }
        postInvalidate();
    }

    @Override
    public int getStrokeColor() {
        return strokePaint == null ? 0 : strokePaint.getColor();
    }
}
