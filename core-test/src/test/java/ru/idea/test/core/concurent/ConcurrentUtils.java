package ru.idea.test.core.concurent;

import java.util.concurrent.TimeUnit;

final class ConcurrentUtils {

    static Thread startNewThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    public static class SimpleRunnable implements Runnable {
        int var;

        SimpleRunnable(int var) {
            this.var = var;
        }

        @Override
        public void run() {
            var++;
        }
    }

    public static class ThreadLocalRunnable implements Runnable {
        private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(threadLocal.get());
        }
    }

    public static class StoppableRunnable implements Runnable{

        private boolean doStop;

        private final long stopDelay;

        public StoppableRunnable(long stopDelay) {
            this.stopDelay = stopDelay;
        }

        public synchronized void doStop() {
            doStop = true;
        }

        private synchronized boolean keepRunning() {
            return !doStop;
        }

        public boolean isStoped(){
            return doStop;
        }

        @Override
        public void run() {
            while(keepRunning()) {
                // keep doing what this thread should do.
                System.out.println("Running: " + Thread.currentThread().getName());

                try {
                    TimeUnit.SECONDS.sleep(stopDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
