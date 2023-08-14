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
 * 增加类方法锁，效率较低，但是可以保证线程安全，无法防止序列化、反射和克隆。
 */
public class LazyTwo implements Cloneable, Serializable {
    private LazyTwo() {}

    private static LazyTwo instance = null;

    public static synchronized LazyTwo getInstance() {
        if (instance == null) {
            instance = new LazyTwo();
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class IntrusiveTest2 {
    public static void main(String[] args) throws Exception {
        LazyTwo obj = LazyTwo.getInstance();
        System.out.println("正常获取的对象" + obj);
        // 反射
        Constructor<LazyTwo> constructor = LazyTwo.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyTwo reflectObj = constructor.newInstance();
        System.out.println("通过反射获取的对象：" + reflectObj);
       
        // 克隆
        LazyTwo cloneObj = (LazyTwo) LazyTwo.getInstance().clone();
        System.out.println("通过克隆获取的对象： " + cloneObj);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(LazyTwo.getInstance());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);

        LazyTwo serializedObj = (LazyTwo) ObjectInputStream.readObject();

        if (byteArrayOutputStream != null) byteArrayOutputStream.close();
        if (objectOutputStream != null) objectOutputStream.close();
        if (byteArrayInputStream != null) byteArrayInputStream.close();
        if (ObjectInputStream != null) ObjectInputStream.close();
        System.out.println("通过序列化获取的对象： " + serializedObj);
    }
}


class ThreadSafeTest2 {
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
                    LazyTwo obj = LazyTwo.getInstance(); 
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
