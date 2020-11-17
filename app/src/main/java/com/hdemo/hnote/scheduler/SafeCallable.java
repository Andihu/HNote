package com.hdemo.hnote.scheduler;

import java.util.concurrent.Callable;

public class SafeCallable<T> implements Callable<T> {
    private Callable<T> callable;

    public SafeCallable(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public T call() {
        // cache exception to avoid crash
        try {
            return callable.call();
        }
        catch (Throwable e) {
            return null;
        }
    }
}
