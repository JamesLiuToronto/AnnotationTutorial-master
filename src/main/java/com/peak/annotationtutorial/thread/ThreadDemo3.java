package com.peak.annotationtutorial.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadResource{
    private int flag = 1 ;  //AA=1, BB=2, CC=3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();



    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try{
            while(flag != 1){
                c1.await();
            }
            for (int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName() + "::" + i + "/" + loop);
            }

            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try{
            while(flag != 2){
                c2.await();
            }
            for (int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + "::" + i + "/" + loop);
            }

            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try{
            while(flag != 3){
                c3.await();
            }
            for (int i=0;i<15;i++){
                System.out.println(Thread.currentThread().getName() + "::" + i + "/" + loop);
            }

            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }

}


public class ThreadDemo3 {

    public void runThread(){
        ThreadResource bean = new ThreadResource();
        new Thread(()->{
            for (int i=0; i<10;i++){
                try {
                    bean.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "AA").start();

        new Thread(()->{
            for (int i=0; i<10;i++){
                try {
                    bean.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "bb").start();

        new Thread(()->{
            for (int i=0; i<10;i++){
                try {
                    bean.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "CC").start();

    }


}
