package com.peak.annotationtutorial.thread;


import java.util.Random;
import java.util.concurrent.*;

public class PoolDemo1 {

    public void runDemo(){
       // ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        //ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        try {
            for (int i=0;i<10;i++){
                final String num = Integer.toString(i) ;
                executorService1.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep((new Random()).nextInt(2));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": running" + num);
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            executorService1.shutdown();
        }

    }

    public void runDemo2(){
        ThreadPoolExecutor customerExecutor =  new ThreadPoolExecutor(2,
        5,
        2,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(3),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy());


        try {
            for (int i=0;i<10;i++){
                final String num = Integer.toString(i) ;
                customerExecutor.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep((new Random()).nextInt(2));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": running" + num);
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            customerExecutor.shutdown();
        }

    }
}
