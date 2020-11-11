package com.hdemo.hnote.utils;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtils {
    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    public static Handler getMainThreadHandler() {
        return sHandler;
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
