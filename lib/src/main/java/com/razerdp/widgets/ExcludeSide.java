package com.razerdp.widgets;

/**
 * Created by 大灯泡 on 2017/11/23.
 */

public interface ExcludeSide {
    int EXCLUDE_ALL = 0x1111;
    int EXCLUDE_NONE = 0;
    int EXCLUDE_LEFT = 0x1000;
    int EXCLUDE_TOP = 0x0100;
    int EXCLUDE_RIGHT = 0x0010;
    int EXCLUDE_BOTTOM = 0x0001;
}
