package com.razerdp.widgets;

import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import static com.razerdp.widgets.IStrokeLayouts.*;

/**
 * Created by 大灯泡 on 2017/11/23.
 * <p>
 * 计算path
 */

class PathHelper {

    /**
     * point0                  point1
     *    ----------------------
     * ｜                       ｜
     * ｜                       ｜
     * ｜                       ｜
     *    ----------------------
     * point3                 point2
     */
    static Path calculatePath(@NonNull View view, @Nullable Path mPath, @Nullable Rect drawRect, int excludeSide) {
        if (view == null) return mPath;
        if (mPath == null) mPath = new Path();
        mPath.reset();

        float point0X = view.getPaddingLeft();
        float point0Y = view.getPaddingTop();

        float point1X = view.getWidth() - view.getPaddingRight();
        float point1Y = point0Y;

        float point2X = point1X;
        float point2Y = view.getHeight() - view.getPaddingBottom();

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
                 *     ｜
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_TOP:
                /**
                 * ｜   ｜
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                mPath.lineTo(point2X, point2Y);
                mPath.lineTo(point1X, point1Y);
                break;
            case EXCLUDE_RIGHT:
                /**
                 *   ---
                 * ｜
                 *   ---
                 */
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_BOTTOM:
                /**
                 *    ---
                 *  ｜   ｜
                 */
                mPath.moveTo(point3X, point3Y);
                mPath.lineTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_LEFT | EXCLUDE_TOP:
                /**
                 *    ｜
                 * ---
                 */
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_LEFT | EXCLUDE_TOP | EXCLUDE_RIGHT:
                /**
                 *
                 * ---
                 */
                mPath.moveTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_LEFT | EXCLUDE_BOTTOM:
                /**
                 *  ---
                 *     ｜
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_LEFT | EXCLUDE_BOTTOM | EXCLUDE_RIGHT:
                /**
                 *  ---
                 *
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                break;
            case EXCLUDE_TOP | EXCLUDE_BOTTOM:
                /**
                 *  ｜   ｜
                 */
                mPath.moveTo(point0X, point1Y);
                mPath.lineTo(point3X, point3Y);
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_RIGHT | EXCLUDE_TOP:
                /**
                 * ｜
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                mPath.lineTo(point2X, point2Y);
                break;
            case EXCLUDE_RIGHT | EXCLUDE_BOTTOM:
                /**
                 *     ---
                 *  ｜
                 */
                mPath.moveTo(point1X, point1Y);
                mPath.lineTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_RIGHT | EXCLUDE_TOP | EXCLUDE_BOTTOM:
                /**
                 * ｜
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point3X, point3Y);
                break;
            case EXCLUDE_LEFT|EXCLUDE_TOP|EXCLUDE_BOTTOM:
                /**
                 *     ｜
                 */
                mPath.moveTo(point1X,point1Y);
                mPath.lineTo(point2X,point2Y);
                break;
            case EXCLUDE_LEFT | EXCLUDE_RIGHT:
                /**
                 *  ---
                 *  ---
                 */
                mPath.moveTo(point0X, point0Y);
                mPath.lineTo(point1X, point1Y);
                mPath.moveTo(point2X, point2Y);
                mPath.lineTo(point3X, point3Y);
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
