package com.peak.annotationtutorial.thread;

import java.awt.image.VolatileImage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MapCache{
    private volatile Map<String, String> map = new HashMap<String, String>();
    ReadWriteLock lock = new ReentrantReadWriteLock() ;

    public void put(String key, String value){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + ": writing");
        try {
            TimeUnit.SECONDS.sleep(2);
            map.put(key, value) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
        System.out.println(Thread.currentThread().getName() + ": write finish");
    }

    public String get(String key){

        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + ": reading");
            String result = map.get(key) ;
            System.out.println(Thread.currentThread().getName() + ": read finish=" + result);
            return result ;
        } finally {
            lock.readLock().unlock();
        }

    }


}

public class ReadWriteDemo {
    public void runDemo(){
        MapCache mapCache = new MapCache();
        for (int i=0; i<5; i++){
            final String num = Integer.toString(i) ;
            new Thread(()->{
                mapCache.put(num, num);
            }, Integer.toString(i)).start();
        }

        for (int i=0; i<5; i++){
            final String num = Integer.toString(i) ;
            new Thread(()->{
                mapCache.get(num) ;
            }, Integer.toString(i)).start();
        }
    }


}
