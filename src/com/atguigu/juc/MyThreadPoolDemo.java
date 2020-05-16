package com.atguigu.juc;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
        5,
        1L,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(3),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void JDKThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            for (int i = 1; i <=20 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
