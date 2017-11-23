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
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StrokeLinearLayout);
            excludeSide = a.getInt(R.styleable.StrokeLinearLayout_exclude_side, EXCLUDE_NONE);
            strokeWidth = a.getDimensionPixelSize(R.styleable.StrokeLinearLayout_stroke_width, 2);
            strokeColor = a.getColor(R.styleable.StrokeLinearLayout_stroke_color, 0xFFCCCCCC);
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
            mPath = calculatePath(mDrawRect);
            canvas.drawPath(mPath, strokePaint);
        }
        super.onDraw(canvas);
    }

    /**
     * point0                  point1
     * ----------------------
     * \                       \
     * \                       \
     * \                       \
     * ----------------------
     * point3                 point2
     */
    private Path calculatePath(Rect drawRect) {
        mPath.reset();

        float point0X = getPaddingLeft();
        float point0Y = getPaddingTop();

        float point1X = getWidth() - getPaddingRight();
        float point1Y = point0Y;

        float point2X = point1X;
        float point2Y = getHeight() - getPaddingBottom();

        float point3X = point0X;
        float point3Y = point2Y;

        if (drawRect != null && !drawRect.isEmpty()) {
            point0X = drawRect.left;
            point0Y = drawRect.top;

            point1X = drawRect.right;
            point1Y = point0Y;

            point2X = point1X;
            point2Y = drawRect.bottom;

            point3X = point0X;
            point3Y = point2Y;
        }

        switch (excludeSide) {
            case EXCLUDE_LEFT:
                /**
                 *  ---
                 *     \
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_TOP:
                /**
                 * \   \
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                mPath.lineTo(point2X, point2Y);
                mPath.lineTo(point1X, point1Y);
                break;
            case EXCLUDE_RIGHT:
                /**
                 *  ---
                 * \
                 *  ---
                 */
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_BOTTOM:
                /**
                 *   ---
                 *  \   \
                 */
                mPath.moveTo(point3X, point3Y);
                mPath.lineTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_LEFT + EXCLUDE_TOP:
                /**
                 *    \
                 * ---
                 */
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_LEFT + EXCLUDE_TOP + EXCLUDE_RIGHT:
                /**
                 *
                 * ---
                 */
                mPath.moveTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_LEFT + EXCLUDE_BOTTOM:
                /**
                 *  ---
                 *     \
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_LEFT + EXCLUDE_BOTTOM + EXCLUDE_RIGHT:
                /**
                 *  ---
                 *
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                break;
            case EXCLUDE_TOP + EXCLUDE_BOTTOM:
                /**
                 *  \   \
                 */
                mPath.moveTo(point0X, point1Y);
                mPath.lineTo(point3X, point3Y);
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_RIGHT + EXCLUDE_TOP:
                /**
                 * \
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_RIGHT + EXCLUDE_BOTTOM:
                /**
                 *   ---
                 *  \
                 */
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_RIGHT + EXCLUDE_TOP + EXCLUDE_BOTTOM:
                /**
                 * \
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_LEFT + EXCLUDE_RIGHT:
                /**
                 *  ---
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.moveTo(point3X, point3Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_NONE:
            default:
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
                mPath.lineTo(point0X, point0Y);
                break;
        }
        return mPath;
    }
}
