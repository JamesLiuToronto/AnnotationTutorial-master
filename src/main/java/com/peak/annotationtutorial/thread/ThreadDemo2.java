package com.peak.annotationtutorial.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class ShareLock{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition() ;

    public void incre() throws InterruptedException {
        lock.lock();
        try {
            while (number == 1) {
                condition.await();
            }
            number++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
        log.info(Thread.currentThread().getName() + "::" + number);


    }

    public void descr() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
        log.info(Thread.currentThread().getName() + "::" + number);

    }
}


public class ThreadDemo2 {
    public void runThread() {
        ShareLock share = new ShareLock();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incre();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.descr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "BB").start();

    }

}
