package com.hdemo.hnote.ui.widget;

import android.content.Context;

/**
 * Copyright (C), 2015-2020
 * FileName: DisplayUtils
 * Author: hujian
 * Date: 2020/11/16 16:44
 * History:
 * <author> <time> <version> <desc>
 */
public class DisplayUtils {
    public static float dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }
}
