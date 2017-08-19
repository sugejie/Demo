package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {
    /**
     * 打印日志,注意使用slf4j包中的Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 此方法写在@Before和@After中，避免重复写execution....,减少冗余
     */
    @Pointcut("execution(public * com.example.controller.GirlController.*(..))")
    public void log() {
    }

    /**
     * 获取请求信息
     * @param joinPoint 该参数用于获取类方法和参数
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        /** 获取请求 */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //http method：POST PUT DELETE GET...
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());
    }

    /**
     * 在return之前调用
     */
    @After("log()")
    public void doAfter() {
        logger.info("do after");
    }

    /**
     * 获取返回参数
     * @param object 有参数一定要在@AfterReturning很中写returning参数
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }


}
