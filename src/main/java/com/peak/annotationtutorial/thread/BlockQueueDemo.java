package com.peak.annotationtutorial.thread;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueueDemo {

    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(4) ;
    volatile int count = 0;

    public void runDemo(){
        for (Integer i = 0;i<20;i ++){
            final String num = Integer.toString(i) ;
            new Thread(()->{

                try {

                    Thread.sleep(2000);
                    blockingQueue.put(num);
                    System.out.println(Thread.currentThread().getName() + ":put=" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + ":failed=" + num);
                }

            }, num ).start();
        }

        for (Integer i = 0;i<20;i ++){
            final String num = Integer.toString(i) ;
            new Thread(()->{

                try {
                    String result = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + ":take=" + result +
                            " total = " + blockingQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

            }, num ).start();
        }
    }

    public void runDemoProducerConsumer(){

        final Runnable producer= ()->{
            //while (true){
                try {
                    blockingQueue.put(Integer.toString(count));
                    count++ ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            //}
        };
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();

        final Runnable consumer= ()->{
            //while (true){
                try {
                    String result = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + ":" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            //}
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        System.out.println("size items " + blockingQueue.size());
        System.out.println("left items " + blockingQueue.stream().count());
    }




}
