package com.github.mobile.apectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
//import java.io.IOException;
import android.util.Log;
import java.lang.reflect.Method;

@Aspect
public class ExAspect {
    private static final String POINTCUT_METHOD = "execution(void onException*(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@org.github.mobile.aspectj.DebugTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {}

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Method method = methodSignature.getMethod();
        Ex myAnnotation = method.getAnnotation(Ex.class);
        String tag = myAnnotation.tag();
        String msg = myAnnotation.msg();
        Object[] args = joinPoint.getArgs();
        Exception e = (Exception) args[0];
        Object result = joinPoint.proceed();
        Log.d(className, msg+tag, e);

        return result;
    }

}
