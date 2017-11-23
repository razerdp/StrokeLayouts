package com.razerdp.widgets;

/**
 * Created by 大灯泡 on 2017/11/23.
 */

interface IStrokeLayouts extends ExcludeSide{


    void setExcludeSide(int excludeSide);

    int getExcludeSide();

    void setStrokeWidth(float strokeWidth);

    float getStrokeWidth();

    void setStrokeColor(int strokeColor);

    int getStrokeColor();
}
