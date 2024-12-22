package com.pir_online.test_assignment_pir.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.pir_online.test_assignment_pir..*.*(..)) " +
            "&& !within(com.pir_online.test_assignment_pir.controller.ControllerAdvice)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("{} method execution start", joinPoint.getSignature().toString());
        Object returnObj = joinPoint.proceed();
        log.info("{} method execution end", joinPoint.getSignature().toString());
        return returnObj;
    }

    @AfterThrowing(
            value = "execution(* com.pir_online.test_assignment_pir..*(..))",
            throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        log.warn("{} An exception happened due to : {}", joinPoint.getSignature(), e.getMessage());
    }
}
