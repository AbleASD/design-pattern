package com.able.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @Before：这种拦截器先执行拦截代码，再执行目标代码。如果拦截器抛异常，那么目标代码就不执行了；
 * 
 * @After：这种拦截器先执行目标代码，再执行拦截器代码。无论目标代码是否抛异常，拦截器代码都会执行；
 * 
 * @AfterReturning：和@After不同的是，只有当目标代码正常返回时，才执行拦截器代码；
 * 
 * @AfterThrowing：和@After不同的是，只有当目标代码抛出了异常时，才执行拦截器代码；
 * 
 * @Around：能完全控制目标代码是否执行，并可以在执行前后、抛异常后执行任意拦截代码，可以说是包含了上面所有功能。
 */

@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(com.able.aop.log.MyAnnotation)")
    public void aspect() {

    }

    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer("[Before]: \n");

        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();

        Class<? extends JoinPoint> clazz = joinPoint.getClass();
        sb.append("Class Name: " + clazz.getName() + "\n");
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Class<?> declaringType = methodSignature.getDeclaringType();
            String[] parameterNames = methodSignature.getParameterNames();
            Class<?>[] parameterTypes = methodSignature.getParameterTypes();
            int parameterLength = parameterNames.length;
            sb.append("Declaring Type: " + declaringType.getName() + "\n");
            for (int i = 0; i < parameterLength; ++i) {
                sb.append(
                        "Arg Index [" + i + "], " +
                                "Arg Name [" + parameterNames[i] + "], " +
                                "Arg Type [" + parameterTypes[i] + "], " +
                                "Arg Value [" + args[i] + "], ");
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer("[After]: \n");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        String targetName = joinPoint.getTarget().getClass().getName();
        sb.append("Target Name:" + targetName + "\n");
        
        Class<?> returnType = methodSignature.getReturnType();
        sb.append("Return Type: " + returnType + "\n");
        System.out.println(sb);
    }

    @AfterReturning(pointcut = "aspect()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
         StringBuffer sb = new StringBuffer("[AfterReturning]: \n");
         sb.append("Return value: " + returnValue);
         System.out.println(sb);
    }

    @AfterThrowing(pointcut = "aspect()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("afterThrowing: " + exception);
    }

    // @Around("aspect()")
    // public void around(ProceedingJoinPoint joinPoint) throws Throwable {
    // String value = "my.value()";
    // System.out.println("[before]: execute" + value);
    // try {
    // joinPoint.proceed();
    // } finally {
    // System.out.println("[after]: execute" + value);
    // }
    // }
}
