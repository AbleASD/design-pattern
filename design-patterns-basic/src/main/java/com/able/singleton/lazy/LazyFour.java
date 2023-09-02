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
 * 内部静态类，参数传递无法实现，线程安全，无法防止序列化、反射和克隆
 */
public class LazyFour implements Cloneable, Serializable {
    private LazyFour() {}

    // final 保证方法不被重写
    public static final LazyFour getInstance() {
        return LazyHolder.LAZY;
    }
    // 内部静态类，只有当被调用的时候才会执行类加载，且类静态属性只会在第一次加载类时初始化，从而保证单例
    private static class LazyHolder {
        private static final LazyFour LAZY = new LazyFour();
        
    }
        
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class IntrusiveTest4 {
    public static void main(String[] args) throws Exception {
        LazyFour obj = LazyFour.getInstance();
        System.out.println("正常获取的对象" + obj);
        // 反射
        Constructor<LazyFour> constructor = LazyFour.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyFour reflectObj = constructor.newInstance();
        System.out.println("通过反射获取的对象：" + reflectObj);
       
        // 克隆
        LazyFour cloneObj = (LazyFour) LazyFour.getInstance().clone();
        System.out.println("通过克隆获取的对象： " + cloneObj);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(LazyFour.getInstance());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);

        LazyFour serializedObj = (LazyFour) ObjectInputStream.readObject();

        if (byteArrayOutputStream != null) byteArrayOutputStream.close();
        if (objectOutputStream != null) objectOutputStream.close();
        if (byteArrayInputStream != null) byteArrayInputStream.close();
        if (ObjectInputStream != null) ObjectInputStream.close();
        System.out.println("通过序列化获取的对象： " + serializedObj);
    }
}


class ThreadSafeTest4 {
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
                    LazyFour obj = LazyFour.getInstance(); 
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