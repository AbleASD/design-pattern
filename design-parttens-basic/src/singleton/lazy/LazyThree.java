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
 * 不能防止反射、序列化和克隆的破坏
 * 防止反射：增加一个静态属性
 * 防止克隆：重写克隆方法
 * 防止序列化：添加readResolve()
 * 
 */
public class LazyThree implements Cloneable, Serializable {

    // volatile 防止 jvm 乱序执行 从而导致的异常
    private static volatile LazyThree instance = null;

    private LazyThree() {}

    // Double-Check 线程安全
    public static LazyThree getInstance() {
        if (instance == null) {
            synchronized(LazyThree.class) {
                if (instance == null) {
                    instance = new LazyThree();
                }
            }
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


class IntrusiveTest3 {
    public static void main(String[] args) throws Exception {
        LazyThree obj = LazyThree.getInstance();
        System.out.println("正常获取的对象" + obj);
        // 反射
        Constructor<LazyThree> constructor = LazyThree.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyThree reflectObj = constructor.newInstance();
        System.out.println("通过反射获取的对象：" + reflectObj);
       
        // 克隆
        LazyThree cloneObj = (LazyThree) LazyThree.getInstance().clone();
        System.out.println("通过克隆获取的对象： " + cloneObj);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(LazyThree.getInstance());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);

        LazyThree serializedObj = (LazyThree) ObjectInputStream.readObject();

        if (byteArrayOutputStream != null) byteArrayOutputStream.close();
        if (objectOutputStream != null) objectOutputStream.close();
        if (byteArrayInputStream != null) byteArrayInputStream.close();
        if (ObjectInputStream != null) ObjectInputStream.close();
        System.out.println("通过序列化获取的对象： " + serializedObj);
    }
}


class ThreadSafeTest3 {
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
                    LazyThree obj = LazyThree.getInstance(); 
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



    // private LazyThree() {
    //     if (isFirstCreate) {
    //         synchronized(LazyThree.class) {
    //             if (isFirstCreate) {
    //                 isFirstCreate = false;
    //             }
    //         }
    //     } else {
    //         throw new RuntimeException("已经实例化一次了！");
    //     }
    // }