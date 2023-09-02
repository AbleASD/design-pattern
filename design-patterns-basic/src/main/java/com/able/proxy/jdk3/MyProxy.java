package com.able.proxy.jdk3;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyProxy {
    public static final String ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces,
            MyInvocationHandler handler) {
        try {
            String src = generateSrc(interfaces);
    
            String filePath = MyProxy.class.getResource("").getPath();
    
            File f = new File(filePath + "$Proxy0.java");
    
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();
    
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iterable = fileManager.getJavaFileObjects(f);
            List<String> options = Arrays.asList("-encoding", "utf-8");
    
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, iterable);
            task.call();
            fileManager.close();
    
            Class<?> proxyClass = classLoader.findClass("$Proxy0");
            Constructor<?> c = proxyClass.getConstructor(MyInvocationHandler.class);
            f.delete();
            return c.newInstance(handler);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
        
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();

        sb.append("package com.able.proxy.jdk3;" + ln);
        sb.append("import com.able.proxy.jdk3.Person;" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);

        sb.append("MyInvocationHandler h;" + ln);

        sb.append("public $Proxy0(MyInvocationHandler h) { " + ln);

        sb.append("this.h = h;");

        sb.append("}" + ln);

        for (Method m : interfaces[0].getMethods()) {
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "() {" + ln);
            sb.append("try{" + ln);
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName()
                    + "\",new Class[]{});" + ln);
            sb.append("this.h.invoke(this,m,null);" + ln);
            sb.append("}catch(Throwable e){" + ln);
            sb.append("e.printStackTrace();" + ln);
            sb.append("}");
            sb.append("}");
        }

        sb.append("}" + ln);

        return sb.toString();
    }

}
