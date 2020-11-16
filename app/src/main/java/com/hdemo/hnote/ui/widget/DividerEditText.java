package com.hdemo.hnote.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Copyright (C), 2015-2020
 * FileName: DividerEditText
 * Author: hujian
 * Date: 2020/11/16 16:24
 * History:
 * <author> <time> <version> <desc>
 */
public class DividerEditText extends androidx.appcompat.widget.AppCompatEditText {
    private Paint linePaint;
    private float margin;
    private int paperColor;
    private Rect mRect;

    public DividerEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        mRect = new Rect();
        linePaint = new Paint();
    }

    protected void onDraw(Canvas paramCanvas) {
        paramCanvas.drawColor(this.paperColor);
        Rect r = mRect;
        int lineCount = getLineCount();
        int height = getHeight();
        int lineHeight = getLineHeight();
        int m = 1 + height / lineHeight;
        if (lineCount < m)
            lineCount = m;
        int n = getLineBounds(0, r);
        paramCanvas.drawLine(0.0F, n, getRight(), n, this.linePaint);
        for (int i = 0;; i++) {
            if (i >= lineCount) {
                setPadding(10 + (int) this.margin, 0, 0, 0);
                super.onDraw(paramCanvas);
                paramCanvas.restore();
                return;
            }
            n += lineHeight;
            paramCanvas.drawLine(0.0F, n, getRight(), n, this.linePaint);
            paramCanvas.save();
        }

    }

}