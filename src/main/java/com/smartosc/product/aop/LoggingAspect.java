package com.smartosc.product.aop;

import com.smartosc.product.aop.services.ApiLogService;
import com.smartosc.product.entity.ApiLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 15:40
 * @created_by Tung lam
 * @since 12/06/2020
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Autowired
    private ApiLogService apiLogService;

    @Pointcut("within(com.smartosc.product..*)")
    public void service() {

    }
    @Around("service()")
    public Object aroundServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed();
    }

    @AfterThrowing(pointcut = "service()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) throws IllegalAccessException {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        log.error("Exception in {}() with message = {}", codeSignature.getDeclaringTypeName(), ex.getMessage());
        ApiLog apiLog = new ApiLog();
        apiLog.setCalledTime(Calendar.getInstance().getTime());
        apiLog.setErrorMessage(ex.getMessage());
        apiLog.setRetryNum(1);
        List<String> args = new ArrayList<>();
        String[] argNames = codeSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        for (int i = 0; i < argNames.length; i++) {
            args.add(argNames[i] + ":" + argValues[i].toString());
        }
        apiLog.setData(String.join(", ", args));
        apiLogService.saveApiLog(apiLog);
        log.error(String.join(", ", args));
    }
}
