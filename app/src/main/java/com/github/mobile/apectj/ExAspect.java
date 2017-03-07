package com.github.mobile.apectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
//import java.io.IOException;
import android.app.Activity;
import android.util.Log;

import com.github.mobile.util.ToastUtils;

import java.lang.reflect.Method;

@Aspect
public class ExAspect {

    private static final String POINTCUT_METHOD =
            "execution(@com.github.mobile.aspectj.Ex * *(..))";
    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.github.mobile.aspectj.Ex *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithEx() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedEx() {}

    @Around("methodAnnotatedWithEx() || constructorAnnotatedEx()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();;
        Method method = methodSignature.getMethod();

        Ex myAnnotation = method.getAnnotation(Ex.class);
        String tag = myAnnotation.tag();
        String msg = myAnnotation.msg();
        Object[] args = joinPoint.getArgs();
        Exception e = (Exception) args[0];

        Object result = joinPoint.proceed();

        // modified
        Log.d(tag, msg, e);
        ToastUtils.show((Activity) joinPoint.getThis(), e.getMessage());

        return result;
    }

}
