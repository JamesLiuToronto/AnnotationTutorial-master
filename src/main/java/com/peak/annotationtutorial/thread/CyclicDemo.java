package com.peak.annotationtutorial.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicDemo {

    private static int NUMBER = 7;
    public void runDemo(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, ()->{
            System.out.println("7 is done");
        }) ;
        for (int i=0;i<8;i++){
            new Thread(()->{
                try{
                    System.out.println(Thread.currentThread().getName() + ": collected");
                    cyclicBarrier.await(5, TimeUnit.SECONDS );
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + ": TIMEOUT");
                }
            }, Integer.toString(i)).start();
        }
    }
}
