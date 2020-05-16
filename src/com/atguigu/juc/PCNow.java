package com.atguigu.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Demo
{
    //定义开始结束标志位
    private volatile boolean FLAG = true;
    //定义一个原子整形
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public Demo(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void Prod() throws Exception
    {
        String Data = null;
        boolean ret;
        while (FLAG)
        {
            Data = atomicInteger.incrementAndGet()+"";
            ret = blockingQueue.offer(Data,2, TimeUnit.SECONDS);
            if (ret)
            {
                System.out.println("插入队列成功" + Data);
            } else {
                System.out.println("插入队列失败");
            }
            TimeUnit.SECONDS.sleep(1);

        }
        System.out.println("生产消费环境结束");
    }

    public void consumer() throws Exception
    {
        String result = null;
        while (FLAG)
        {
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(result==null || result.equalsIgnoreCase(""))
            {
                FLAG = false;
                System.out.println("超时未取出");
                System.out.println();

                return;
            }
            System.out.println("消费队列成功" + result);
        }
    }
    public void stop()
    {
        this.FLAG = false;
    }

}
public class PCNow {
    public static void main(String[] args) {
        Demo demo = new Demo(new ArrayBlockingQueue<>(3));

        new Thread(() -> {
            try {
                demo.Prod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            try {
                demo.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("全部停止");
        demo.stop();
    }
}
