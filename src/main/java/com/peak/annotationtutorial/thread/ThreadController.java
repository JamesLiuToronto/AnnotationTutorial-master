package com.peak.annotationtutorial.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@Slf4j
@RestController
@RequestMapping("thread")
public class ThreadController {



    @PostMapping("/demo1")
    public void postColorCode() {
        ThreadDemo1 demo = new ThreadDemo1();
        demo.runThread();
    }

    @PostMapping("/demo2")
    public void demo2() {
        ThreadDemo2 demo = new ThreadDemo2();
        demo.runThread();
    }

    @PostMapping("/demo3")
    public void demo3() {
        ThreadDemo3 demo = new ThreadDemo3();
        demo.runThread();
    }

    @PostMapping("/demo4")
    public void ticket() {
        TicketSale demo = new TicketSale();
        demo.sales();
    }

    @PostMapping("/demo5")
    public void countDown() throws InterruptedException {
        CountDownDemo demo = new CountDownDemo();
        demo.showDemo();
    }

    @PostMapping("/demo6")
    public void readWrite() throws InterruptedException {
       ReadWriteDemo demo = new ReadWriteDemo();
        demo.runDemo();
    }

    @PostMapping("/demo7")
    public void semphDemo() throws InterruptedException {
        SemphDemo demo = new SemphDemo();
        demo.runDemo();
    }

    @PostMapping("/demo8")
    public void cyclicDemo() throws InterruptedException {
        CyclicDemo demo = new CyclicDemo();
        demo.runDemo();
    }

    @PostMapping("/demo9")
    public void pool1Demo() throws InterruptedException {
        PoolDemo1 demo = new PoolDemo1();
        demo.runDemo2();
    }

    @PostMapping("/demo10")
    public void forkDemo() throws InterruptedException, ExecutionException {
        ForkJoinDemo demo = new ForkJoinDemo();
        demo.runDemo();
    }

    @PostMapping("/demo11")
    public void BlockQueueDemo() throws InterruptedException, ExecutionException {
        BlockQueueDemo demo = new BlockQueueDemo();
        demo.runDemo();
    }

    @PostMapping("/demo12")
    public void MyBlockQueueDemo() throws InterruptedException, ExecutionException {
        MyBlockingQueueDemo demo = new MyBlockingQueueDemo();
        demo.runDemo();
    }


}
