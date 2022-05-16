package com.peak.annotationtutorial.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class RunDemo implements Runnable {

    @Override
    public void run() {

    }
}

class CallDemo implements Callable {


    @Override
    public Integer call() throws Exception {
        return 22;
    }
}

public class CallableDemo {

    public void callThread() throws ExecutionException, InterruptedException {
        new Thread(new RunDemo(), "AA").start();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallDemo()) ;
        Integer result = futureTask.get();

        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(()->{
           return 5 ;
        });

        new Thread(futureTask2, "cc").start();
        Integer result2 = futureTask2.get();



    }
}
