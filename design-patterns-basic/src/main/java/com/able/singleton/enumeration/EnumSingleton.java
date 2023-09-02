package com.able.singleton.enumeration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 枚举单例
 */
public enum EnumSingleton {
    INSTANCE;

    private EnumSingleton() {
    };

    // Cannot override the final method from Enum<EnumSingleton>
    // @Override
    // protected Object clone() throws CloneNotSupportedException {
    // return super.clone();
    // }
}

class Intrusive {
    public static void main(String[] args) throws Exception {
        EnumSingleton obj = EnumSingleton.INSTANCE;
        System.out.println("正常获取的对象" + obj);
        // 反射
        Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        EnumSingleton reflectObj = constructor.newInstance();
        System.out.println("通过反射获取的对象：" + reflectObj);

        // 枚举类型的父类 Enum.class 中的 clone 方法被 final 修饰，无法被重写，该方法直接 throw CloneNotSupportedException
        // 因此枚举类不支持克隆
        // EnumSingleton cloneObj = (EnumSingleton) EnumSingleton.INSTANCE.clone();
        // System.out.println("通过克隆获取的对象： " + cloneObj);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(EnumSingleton.INSTANCE);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);

        EnumSingleton serializedObj = (EnumSingleton) ObjectInputStream.readObject();

        if (byteArrayOutputStream != null)
            byteArrayOutputStream.close();
        if (objectOutputStream != null)
            objectOutputStream.close();
        if (byteArrayInputStream != null)
            byteArrayInputStream.close();
        if (ObjectInputStream != null)
            ObjectInputStream.close();
        System.out.println("通过序列化获取的对象： " + serializedObj);
    }
}

class ThreadSafe {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    EnumSingleton obj = EnumSingleton.INSTANCE;
                    System.out.println(System.currentTimeMillis() + "===" + obj);
                }
            };
            thread.start();
            threads.add(thread);
            latch.countDown();
        }
        for (Thread thread : threads) {
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