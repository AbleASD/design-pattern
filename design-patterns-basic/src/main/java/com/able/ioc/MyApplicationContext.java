package com.able.ioc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.able.ioc.annotation.MyAutowired;
import com.able.ioc.annotation.MyComponent;
import com.able.ioc.utils.IocUtil;

public class MyApplicationContext {
    private Set<String> classNameSet = new HashSet<String>();
    private Map<String, Object> beanMap = new HashMap<String, Object>();

    public MyApplicationContext() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try {
            Object object = beanMap.get(name);
            objectOutputStream.writeObject(object);
            Object readObject = (Object) objectInputStream.readObject();
            // return readObject;
            return readObject;
        } finally {
            if (byteArrayInputStream != null)
                byteArrayOutputStream.close();
            if (objectOutputStream != null)
                objectOutputStream.close();
            if (byteArrayInputStream != null)
                byteArrayInputStream.close();
            if (objectInputStream != null)
                objectInputStream.close();
        }
        
    }

    public void init() throws Exception {
        String beanScanPath = getBeanScanPath("ioc.beans.scan");

        loadBeanClass(beanScanPath);

        registerBean();

        dependenceInjection();
    }

    /**
     * 扫描包并获得类加载器
     * 
     * @param packageName
     */
    public void loadBeanClass(String packageName) {
        String filePath = packageName.replace(".", "/");

        URL url = this.getClass().getClassLoader().getResource(filePath);

        File rootFile = new File(url.getFile());

        if (rootFile != null) {
            for (File file : rootFile.listFiles()) {
                if (file.isDirectory()) {
                    loadBeanClass(packageName + "." + file.getName());
                } else {
                    if (file.getName().indexOf(".class") > 0) {
                        classNameSet.add(packageName + "." + file.getName().replace(".class", ""));
                    }
                }
            }
        }
    }

    private String getBeanScanPath(String key) {
        Properties properties = IocUtil.getPropertyByName("application.properties");
        return properties.getProperty(key).toString();
    }

    /**
     * 注册
     * 
     * @throws Exception
     */
    private void registerBean() throws Exception {
        if (!classNameSet.isEmpty()) {

            for (String className : this.classNameSet) {
                Class<?> clazz = Class.forName(className);

                MyComponent myComponent = (MyComponent) clazz.getAnnotation(MyComponent.class);
                if (myComponent == null)
                    continue;

                String beanName = (myComponent.value().isEmpty()) ? IocUtil.toLowercaseIndex(clazz.getSimpleName())
                        : myComponent.value();
                beanMap.put(beanName, clazz.getDeclaredConstructor().newInstance());
            }
        }
    }

    /**
     * 依赖注入
     * 
     * @throws Exception
     */
    private void dependenceInjection() throws Exception {
        if (beanMap.isEmpty())
            return;
        for (Object object : beanMap.values()) {
            doInjection(object);
        }
    }

    private void doInjection(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();

        if (fields != null && fields.length != 0) {
            for (Field field : fields) {
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                if (autowired != null) {
                    String beanName = (autowired.value().isEmpty())
                            ? IocUtil.toLowercaseIndex(field.getType().getSimpleName())
                            : autowired.value();
                    if (!beanMap.containsKey(beanName)) {
                        Class<?> clazz = field.getType();
                        beanMap.put(beanName, clazz.getDeclaredConstructor().newInstance());
                    }
                    field.setAccessible(true);
                    field.set(object, beanMap.get(beanName));
                    doInjection(beanMap.get(beanName));
                }
            }
        }
    }
}
