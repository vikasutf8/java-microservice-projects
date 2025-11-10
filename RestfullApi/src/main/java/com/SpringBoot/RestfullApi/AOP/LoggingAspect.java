package com.SpringBoot.RestfullApi.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut → matches all methods in service package
    @Pointcut("execution(* com.SpringBoot.RestfullApi.Service.StudentService.createStudent(..))")
    public void serviceMethods() {}

    // Before advice
    @Before("serviceMethods()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("[BEFORE] Method called: " + joinPoint.getSignature().getName());
    }

    // After returning advice
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterMethod(JoinPoint joinPoint, Object result) {
        System.out.println("[AFTER] Method finished: " + joinPoint.getSignature().getName());
        System.out.println("[RESULT] => " + result);
    }
}
