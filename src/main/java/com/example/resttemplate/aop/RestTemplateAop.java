package com.example.resttemplate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
@Slf4j
public class RestTemplateAop {

    @Pointcut("execution(* com.example.resttemplate.controller..*(..))")
    private void cut(){}

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        log.info("============================================");
        log.info("AOP before start");

        String shortString = joinPoint.getSignature().toShortString();
        String name = joinPoint.getSignature().getName();
        Class declaringType = joinPoint.getSignature().getDeclaringType();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        log.info("shortString: {}", shortString);
        log.info("name: {}", name);
        log.info("declaringType: {}", declaringType);
        log.info("declaringTypeName: {}", declaringTypeName);

        int i = 0;
        for (Object arg: args) {
            log.info("arg[{}], {}", i, arg);
        }

        log.info("AOP before end");
        log.info("============================================");
    }

    @Around("cut()")
    public void around(ProceedingJoinPoint joinPoint){
        log.info("============================================");
        log.info("AOP around start");

        Object[] args = joinPoint.getArgs();

        int i = 0;
        for (Object arg: args) {
            log.info("arg[{}], {}", i, arg);
        }

        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.info("AOP around end");
        log.info("============================================");
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj){
        log.info("============================================");
        log.info("AOP afterReturning start");

        log.info("returnObj: {}", returnObj);

        log.info("AOP afterReturning end");
        log.info("============================================");
    }

    @After("cut()")
    public void after(JoinPoint joinPoint){
        log.info("============================================");
        log.info("AOP after start");

        String shortString = joinPoint.getSignature().toShortString();
        String name = joinPoint.getSignature().getName();
        Class declaringType = joinPoint.getSignature().getDeclaringType();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        log.info("shortString: {}", shortString);
        log.info("name: {}", name);
        log.info("declaringType: {}", declaringType);
        log.info("declaringTypeName: {}", declaringTypeName);

        int i = 0;
        for (Object arg: args) {
            log.info("arg[{}], {}", i, arg);
        }

        log.info("AOP after end");
        log.info("============================================");
    }

}
