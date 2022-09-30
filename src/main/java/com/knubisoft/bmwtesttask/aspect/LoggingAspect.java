package com.knubisoft.bmwtesttask.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public LoggingAspect(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {

    }

    @Pointcut("within(com.knubisoft.bmwtesttask.controller.impl..*)")
    public void controllerPointcut() {

    }

    @Pointcut("within(com.knubisoft.bmwtesttask.service..*)")
    public void servicePointcut() {

    }

    @AfterThrowing(pointcut = "springBeanPointcut() && controllerPointcut() && servicePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() message = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getMessage() != null ? e.getMessage() : "NULL");
    }

    @Around("springBeanPointcut() && controllerPointcut() && servicePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.info("request={}, response={}, status={}, time={} ms",
                        request.getRequestURL(),
                        response,
                        response.getStatus(),
                        System.currentTimeMillis() - start);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw new RuntimeException(e.getCause());
        }
    }
}
