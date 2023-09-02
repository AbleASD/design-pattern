package com.able.proxy.jdk;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

import com.able.proxy.common.IUserDao;
import com.able.proxy.common.UserDao;


public class Test {
    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws Exception {
        IUserDao target = new UserDao();
        System.out.println(target.getClass());  // class proxy.common.UserDao

        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());  // class com.sun.proxy.$Proxy0
        
        String proxyName = "Proxy0";
        
        Class<?>[] clazz = new Class<?>[]{IUserDao.class};
        
        Class<?> c1 = Class.forName("java.lang.reflect.ProxyGenerator");

        Method m = c1.getDeclaredMethod("generateProxyClass", String.class, Class[].class);
        m.setAccessible(true);

        byte[] bytes = (byte[]) m.invoke(null, proxyName, clazz);

        FileOutputStream os = new FileOutputStream("D:/project/design-pattern/design-patterns-basic/src/main/java/com/able/proxy/jdk/output/$Proxy0.class");

        os.write(bytes);
        os.close();
        proxy.save();
        System.out.println(System.getProperty("java.home"));

    }
}
