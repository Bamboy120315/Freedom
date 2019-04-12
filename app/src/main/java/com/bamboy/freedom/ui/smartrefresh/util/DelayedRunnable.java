package com.bamboy.freedom.ui.smartrefresh.util;

public class DelayedRunnable implements Runnable {
    public long delayMillis;
    private Runnable runnable;
    public DelayedRunnable(Runnable runnable, long delayMillis) {
        this.runnable = runnable;
        this.delayMillis = delayMillis;
    }
    @Override
    public void run() {
        try {
            if (runnable != null) {
                runnable.run();
                runnable = null;
            }
        } catch (Throwable e) {
            if (!(e instanceof NoClassDefFoundError)) {
                e.printStackTrace();
            }
        }
    }
}