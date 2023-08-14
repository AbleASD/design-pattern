package singleton.hunger;

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
 * 饿汉模式，类加载时即创建了实例，当饿汉较多时容易造成效率较低的问题
 */
public class Hunger implements Cloneable, Serializable {
    private Hunger() {}

    // final static 修饰, 改属性不可更改且在类加载时初始化。线程安全
    private static final Hunger instance = new Hunger();

    public static Hunger getInstance() {
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class IntrusiveTest {
    public static void main(String[] args) throws Exception {
        Hunger obj = Hunger.getInstance();
        System.out.println("正常获取的对象" + obj);
        // 反射
        Constructor<Hunger> constructor = Hunger.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Hunger reflectObj = constructor.newInstance();
        System.out.println("通过反射获取的对象：" + reflectObj);
       
        // 克隆
        Hunger cloneObj = (Hunger) Hunger.getInstance().clone();
        System.out.println("通过克隆获取的对象： " + cloneObj);

        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(Hunger.getInstance());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);

        Hunger serializedObj = (Hunger) ObjectInputStream.readObject();

        if (byteArrayOutputStream != null) byteArrayOutputStream.close();
        if (objectOutputStream != null) objectOutputStream.close();
        if (byteArrayInputStream != null) byteArrayInputStream.close();
        if (ObjectInputStream != null) ObjectInputStream.close();
        System.out.println("通过序列化获取的对象： " + serializedObj);
    }
}

class ThreadSafeTest {
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
                    Hunger obj = Hunger.getInstance(); 
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
