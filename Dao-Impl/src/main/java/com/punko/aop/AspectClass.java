package com.punko.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectClass {

    @Around("execution(* com.punko.daoImpl.*.*(..))")
    public Object aroundAllMethodAdviceLogger(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println(methodName + " started his work");
        Object targetMethod = null;

        try {
            targetMethod = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e + "exception was caught");
            throw e;
        }

        System.out.println(methodName + " finished his work");

        return targetMethod;
    }

}
