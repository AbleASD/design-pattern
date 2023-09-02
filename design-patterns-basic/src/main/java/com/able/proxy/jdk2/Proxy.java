package com.able.proxy.jdk2;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.apache.commons.io.FileUtils;

public class Proxy {
    private static final String RT = "\n\n";

    public static Object newProxyInstance() throws Exception {
        String sourceCode = 
            "package com.able.proxy.jdk2.simulate;" + RT +
            "import com.able.proxy.jdk2.Admin;" + RT +
            "import com.able.proxy.jdk2.Manager;" + RT +
            "//以聚合方式实现的代理主题" + RT +
            "public class $Proxy0 implements Manager {" + RT +
            "   private Admin admin;" + RT +
            "   public $Proxy0(Admin admin) {" + RT +
            "       super();" + RT +
            "       this.admin= admin;" + RT +
            "   }" + RT +
            "   public void doSomething() {" + RT +
            "       System.out.println(\"Log:admin操作开始....\");" + RT +
            "       admin.doSomething();" + RT +
            "       System.out.println(\"Log:admin操作结束....\");" + RT +
            "   }" + RT +
            "}";

        String fileName = "D:/project/design-pattern/design-patterns-basic/src/main/java/com/able/proxy/jdk2/simulate/$Proxy0.java";

        File file = new File(fileName);
        List<String> options = Arrays.asList("-encoding", "utf-8");
        FileUtils.writeStringToFile(file, sourceCode, "UTF-8", false);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(fileName);
        

        CompilationTask task = compiler.getTask(null, fileManager, null, options, null, javaFileObjects);

        task.call();
        fileManager.close();

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        Class<?> loadClass = systemClassLoader.loadClass("com.able.proxy.jdk2.simulate.$Proxy0");

        Constructor<?> constructor = loadClass.getConstructor(Admin.class);
        
        return constructor.newInstance(new Admin());
    }

}
