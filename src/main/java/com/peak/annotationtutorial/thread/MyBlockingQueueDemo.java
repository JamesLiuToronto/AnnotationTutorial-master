package com.peak.annotationtutorial.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

 public class MyBlockingQueueDemo {

     public void runDemo(){
         MyBlockingQueue<Integer> myqueue = new MyBlockingQueue<>(2);
         new Thread(()->{
             myqueue.put(10);
         }, "aa").start();
         new Thread(()->{
             myqueue.put(20);
         }, "bb").start();
         new Thread(()->{
             myqueue.put(30);
         }, "cc").start();

         new Thread(()->{
             System.out.println(Thread.currentThread().getName() + ":" + myqueue.take());
         }, "dd").start();
         new Thread(()->{
             System.out.println(Thread.currentThread().getName() + ":" + myqueue.take());
         }, "ee").start();
         new Thread(()->{
             System.out.println(Thread.currentThread().getName() + ":" + myqueue.take());
         }, "ff").start();

     }

 }

 class MyBlockingQueue<E>{

    private Queue<E> queue ;
    private int size ;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition fullCondition = lock.newCondition() ;
    private Condition emptyCondition = lock.newCondition() ;

    public MyBlockingQueue(int size){
        this.size = size ;
        queue = new LinkedList<E>() ;
    }

    public void put(E e){
        lock.lock();
        try {
            while (queue.size() == this.size){
                fullCondition.await();
            }
            queue.add(e);
            emptyCondition.signalAll();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public E take(){
        lock.lock();
        try {
            while (queue.size() == 0){
                emptyCondition.await();
            }
            E item = queue.remove();
            fullCondition.signalAll();
            return item;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null ;
    }
}
