package com.hdemo.hnote.scheduler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class Dispatcher {
    private static HandlerThread dispatcher = new HandlerThread("note-handler");
    private static Handler handler;

    public static Handler handler() {
        return handler;
    }

    public static Looper looper() {
        return dispatcher.getLooper();
    }

    static {
        dispatcher.start();
        handler = new Handler(dispatcher.getLooper());
    }
}
