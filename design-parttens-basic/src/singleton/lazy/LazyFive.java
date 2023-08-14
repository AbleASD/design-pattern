package singleton.lazy;

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
 * 以静态内部类为例，解决防止反射、克隆和反序列化的方法。
 */
public class LazyFive implements Cloneable, Serializable {

    // 防止反射，静态变量作为临界资源，标记是否第一次创建对象
    private static boolean isFirstCreate = true;

    private LazyFive() {
        if(isFirstCreate) {
            synchronized(LazyFive.class) {
                if (isFirstCreate) {
                    isFirstCreate = false;
                }
            }
        } else {
            // 当两个进程都通过第一个条件语句时，该异常不会被出发
            throw new RuntimeException("已经实例化一次了");
        }
    }

    // final 保证方法不被重写
    public static final LazyFive getInstance() {
        return LazyHolder.LAZY;
    }
    // 内部静态类，只有当被调用的时候才会执行类加载，且类静态属性只会在第一次加载类时初始化，从而保证单例
    private static class LazyHolder {
        private static final LazyFive LAZY = new LazyFive();
        
    }
    
    // 重写克隆方法直接返回当前实例，
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return LazyFive.getInstance();
    }

    private Object readResolve() {
        return LazyFive.getInstance();
    }

    
}

class IntrusiveTest5 {
    public static void main(String[] args) throws Exception {
        LazyFive obj = LazyFive.getInstance();
        System.out.println("正常获取的对象" + obj);
        // 反射
        Constructor<LazyFive> constructor = LazyFive.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyFive reflectObj = constructor.newInstance();
        System.out.println("通过反射获取的对象：" + reflectObj);
       
        // 克隆
        LazyFive cloneObj = (LazyFive) LazyFive.getInstance().clone();
        System.out.println("通过克隆获取的对象： " + cloneObj);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(LazyFive.getInstance());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);

        LazyFive serializedObj = (LazyFive) ObjectInputStream.readObject();

        if (byteArrayOutputStream != null) byteArrayOutputStream.close();
        if (objectOutputStream != null) objectOutputStream.close();
        if (byteArrayInputStream != null) byteArrayInputStream.close();
        if (ObjectInputStream != null) ObjectInputStream.close();
        System.out.println("通过序列化获取的对象： " + serializedObj);
    }
}


class ThreadSafeTest5 {
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
                    LazyFive obj = LazyFive.getInstance(); 
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