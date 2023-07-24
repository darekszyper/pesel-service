package com.szyperekd.peselservice.advice;

import com.szyperekd.peselservice.exception.InvalidPeselException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    @Pointcut(value = "execution(* com.szyperekd.peselservice.*.*.*(..) )")
    public void loggingPointcut() {
    }

    @Around("loggingPointcut()")
    @SneakyThrows
    public Object applicationLogger(ProceedingJoinPoint pjp) {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        String args = Arrays.toString(pjp.getArgs());
        log.info("method invoked " + className + " : " + methodName + "()" + " arguments : "
                + args);
        Object object = pjp.proceed();
        log.info(className + " : " + methodName + "()" + " Response : "
                + object.toString());
        return object;
    }

    @AfterThrowing(pointcut = "execution(* com.szyperekd.peselservice.service.*.*(..) )", throwing = "ex")
    public void logAfterThrowing(InvalidPeselException ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
    }
}