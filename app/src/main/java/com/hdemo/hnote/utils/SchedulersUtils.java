package com.hdemo.hnote.utils;

import com.hdemo.hnote.scheduler.Dispatcher;
import com.hdemo.hnote.scheduler.Scheduler;
import com.hdemo.hnote.scheduler.ThreadPoolScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class SchedulersUtils {
    private static final Map<String, Scheduler> schedulerMap = new HashMap<>();
    private static final Scheduler currentThreadScheduler = new CurrentThreadScheduler();

    public static Scheduler io() {
        return getScheduler("note-io", 1, 6);
    }

    public static Scheduler net() {
        return getScheduler("note-net", 1, 6);
    }

    public static Scheduler download() {
        return getScheduler("note-download", 0, 10);
    }

    public static Scheduler chatMsg() {
        return getScheduler("note-msg", 3, 10);
    }

    public static Scheduler current() {
        return currentThreadScheduler;
    }

    private static Scheduler getScheduler(String name, int coreSize, int count) {
        Scheduler scheduler = schedulerMap.get(name);
        if (scheduler == null) {
            synchronized (schedulerMap) {
                scheduler = schedulerMap.get(name);
                if (scheduler == null) {
                    scheduler = new ThreadPoolScheduler(name, coreSize, count);
                    schedulerMap.put(name, scheduler);
                }
            }
        }
        return scheduler;
    }

    private static class CurrentThreadScheduler implements Scheduler {

        @Override
        public void execute(Runnable runnable) {
            runnable.run();
        }

        @Override
        public <T> Future<T> submit(Callable<T> callable) {
            throw new IllegalStateException("CurrentThreadScheduler not support submit");
        }

        @Override
        public void execute(Runnable runnable, long delay) {
            Dispatcher.handler().postDelayed(runnable, delay);
        }
    }
}
