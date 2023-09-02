package com.able.singleton.lazy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 懒汉模式，创建第一次创建实例时初始化，多线程不安全，
 */
public class LazyOne implements Cloneable, Serializable {
    private LazyOne() {};
    private static LazyOne instance = null;

    public static LazyOne getInstance() {
        if (instance == null) {
            instance = new LazyOne();
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


class IntrusiveTest1 {
    public static void main(String[] args) throws Exception {
        LazyOne obj = LazyOne.getInstance();
        System.out.println("正常获取的对象" + obj);
        // 反射
        Constructor<LazyOne> constructor = LazyOne.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyOne reflectObj = constructor.newInstance();
        System.out.println("通过反射获取的对象：" + reflectObj);
       
        // 克隆
        LazyOne cloneObj = (LazyOne) LazyOne.getInstance().clone();
        System.out.println("通过克隆获取的对象： " + cloneObj);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(LazyOne.getInstance());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);

        LazyOne serializedObj = (LazyOne) ObjectInputStream.readObject();

        if (byteArrayOutputStream != null) byteArrayOutputStream.close();
        if (objectOutputStream != null) objectOutputStream.close();
        if (byteArrayInputStream != null) byteArrayInputStream.close();
        if (ObjectInputStream != null) ObjectInputStream.close();
        System.out.println("通过序列化获取的对象： " + serializedObj);
    }
}


class ThreadSafeTest1 {
    public static void main(String[] args) {
        int count = 200;
        CountDownLatch latch = new CountDownLatch(count);
        List<Thread> threads = new ArrayList<Thread>();
        

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; ++i) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    
                    try {
                        // 当前线程等待 count 为 0
                        latch.await();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    LazyOne obj = LazyOne.getInstance(); 
                    System.out.println(System.currentTimeMillis() + "===" + obj);
                }
            };
            thread.start();
            threads.add(thread);
            latch.countDown();
        }
        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时： " + (end - start));
    }
}
