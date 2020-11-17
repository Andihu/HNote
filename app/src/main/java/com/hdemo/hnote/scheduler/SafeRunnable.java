package com.hdemo.hnote.scheduler;


public class SafeRunnable implements Runnable {
    private Runnable runnable;

    public SafeRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        // cache exception to avoid crash
        try {
            runnable.run();
        }
        catch (Throwable e) {
        }
    }
}
