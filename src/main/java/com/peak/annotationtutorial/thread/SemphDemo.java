package com.peak.annotationtutorial.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemphDemo {

    public void runDemo(){
        Semaphore semaphore = new Semaphore(2);
        for (int i=0;i<6;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": parking");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + ": release");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, Integer.toString(i)).start();
        }
    }

}
