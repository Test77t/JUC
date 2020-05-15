package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone
{
    public synchronized void SendSMS()
    {
        System.out.println(Thread.currentThread().getId() + "消息发送中");
        sendEmail();
    }
    public synchronized void sendEmail()
    {
        System.out.println(Thread.currentThread().getId() + "发送Email中");
    }
}


public class Locktest {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.SendSMS();

    }
}
