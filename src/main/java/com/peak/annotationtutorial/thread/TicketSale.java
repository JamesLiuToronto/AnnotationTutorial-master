package com.peak.annotationtutorial.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class Ticket{
    private int number = 30 ;
    Lock lock = new ReentrantLock(true);
    Condition condition= lock.newCondition();

    public void sale(){
        lock.lock();
         try{
             if (number > 1)
                number -- ;
             condition.signalAll();
             log.info(Thread.currentThread().getName() + "::" + number);
         } finally {
             lock.unlock();
         }

    }

    public void desale() throws InterruptedException {
        lock.lock();
        try{
            while (number >= 30){
                condition.await();
            }
            number ++ ;
            log.info(Thread.currentThread().getName() + "::" + number);
        } finally {
            lock.unlock();
        }

    }
}


public class TicketSale {

    public void sales(){
        Ticket ticket = new Ticket();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    ticket.sale();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    ticket.sale();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }, "BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.desale();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }, "CC").start();
    }

}
