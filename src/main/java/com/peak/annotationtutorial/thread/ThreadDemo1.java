package com.peak.annotationtutorial.thread;


import lombok.extern.slf4j.Slf4j;

@Slf4j
class Share{
    private int number = 0;

    public synchronized void incre() throws InterruptedException {
        while(number == 1) {
            wait(); // week the place as sleep
        }
        number ++ ;
        log.info(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();

    }

    public synchronized void descr() throws InterruptedException {
        while (number == 0) {
            wait();
        }
        number -- ;
        log.info(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();
    }
}


public class ThreadDemo1 {
    public void runThread(){
        Share share = new Share();
        new Thread(()->{
            for (int i=0;i<10; i++){
                try{
                    share.incre();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }, "AA").start();

        new Thread(()->{
            for (int i=0;i<10; i++){
                try{
                    share.descr();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }, "BB").start();

        new Thread(()->{
            for (int i=0;i<10; i++){
                try{
                    share.incre();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }, "CC").start();

        new Thread(()->{
            for (int i=0;i<10; i++){
                try{
                    share.descr();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }, "DD").start();
    }
}
