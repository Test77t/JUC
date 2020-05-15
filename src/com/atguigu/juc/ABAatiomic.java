package com.atguigu.juc;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.atomic.AtomicReference;

class User
{
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


public class ABAatiomic {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user = new User("zhangsan", "123");
        User user2 = new User("lisi", "123");
        atomicReference.set(user);
        System.out.println(atomicReference.compareAndSet(user, user2) + "update user" + atomicReference.toString());
        System.out.println(atomicReference.compareAndSet(user, user2) + "update user");
    }



}
