package com.hdemo.hnote.scheduler;


import android.os.Handler;

public abstract class ScheduledTask implements Runnable {
    private boolean isStarted = false;
    private Scheduler scheduler;
    private long retryInterval = 1000 * 60 * 10L;

    public ScheduledTask(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 获取运行间隔
     * @return  间隔时间（毫秒）
     */
    public abstract long getInterval();

    /**
     * 运行间隔因子，运行时间除以因子为真正的间隔时间
     * @return
     */
    public int getIntervalFactor() {
        return 1;
    }

    /**
     * 保存上次执行时间
     * @param time
     */
    protected abstract void setLastRunTime(long time);

    /**
     * 获取上次执行时间
     * @return
     */
    protected abstract long getLastRunTime();

    protected abstract boolean execute();

    /**
     * execute 返回 false时，下次运行间隔
     * @return
     */
    protected void setRetryInterval(long retryInterval) {
        this.retryInterval = retryInterval;
    }

    protected long getRetryInterval() {
        return retryInterval;
    }

    /**
     * 获取下次执行时间间隔
     * @return
     */
    private long getNextRunInterval() {
        long now = System.currentTimeMillis();
        long diff = getInterval() / getIntervalFactor() - (now - getLastRunTime());
        return diff <= 0 ? 100 : diff;
    }

    public synchronized void start() {
        if (!isStarted) {
            isStarted = true;
            Dispatcher.handler().postDelayed(this, getNextRunInterval());
        }
    }

    public synchronized void stop() {
        isStarted = false;
        Dispatcher.handler().removeCallbacks(this);
    }

    @Override
    public void run() {
        scheduler.execute(new Runnable() {
            @Override
            public void run() {
                boolean result = execute();
                setLastRunTime(result ? System.currentTimeMillis() : System.currentTimeMillis() - getInterval() / getIntervalFactor() + getRetryInterval());
                // 删除队列中已经存在的当前任务，保证任意时刻只有一个对应任务存在于队列中
                Handler dispatcher = Dispatcher.handler();
                dispatcher.removeCallbacks(this);
                dispatcher.postDelayed(this, getNextRunInterval());
            }
        });
    }
}
