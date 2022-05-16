package com.peak.annotationtutorial.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {

    class MyTask extends RecursiveTask<Integer>{

        int begin ;
        int end ;

        public MyTask(int begin, int end){
            this.begin = begin ;
            this.end = end ;
        }

        @Override
        protected Integer compute() {
            if (end - begin <= 10){
                int result = 0;
                for (int i=begin ;i<=end;i++){
                    result = result + i ;
                }
                return result ;
            }
            int middle = (begin + end ) /2 ;
            // first
            MyTask task1 = new MyTask(begin, middle) ;
            // second
            MyTask task2 = new MyTask(middle+1, end) ;

            task1.fork();
            task2.fork() ;
            int result = task1.join() + task2.join() ;
            return result;
        }
    }


    public void runDemo() throws ExecutionException, InterruptedException {
        MyTask mytask = new MyTask(1,100) ;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(mytask);
        Integer result = task.get();
        System.out.println("final result " + result);
        forkJoinPool.shutdown();
    }
}
