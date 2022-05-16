package com.peak.annotationtutorial.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownDemo {

    CountDownLatch countDownLatch = new CountDownLatch(6) ;

    public void showDemo() throws InterruptedException {

        for (int i=0;i<6;i++){
            new Thread(()-> {
            }, Integer.toString(i)).start();
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + ":" + Integer.toString(i));
        }

        countDownLatch.await();
        System.out.println("It is closed !");

    }
}
