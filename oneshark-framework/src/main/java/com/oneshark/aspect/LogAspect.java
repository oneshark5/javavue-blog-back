package com.oneshark.aspect;

/**
 * 切面类
 *  两点：确定切点、通知方法（即增强的代码）
 */

import com.alibaba.fastjson.JSON;
import com.oneshark.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component // 注入容器
@Aspect // 说明这是一个切面类
@Slf4j
public class LogAspect {

    /**
     * 指定切点 --- 这里通过注解进行标识,已经定义了SystemLog注解，
     * 希望后面加上SystemLog注解的方法都会去进行一个日志的打印log记录
     * @annotation()接收的参数为全类名
     */
    @Pointcut("@annotation(com.oneshark.annotation.SystemLog)")
    public void pt() {

    }

    // 定义通知方法，这里是环绕通知，环绕通知功能最强大---环绕通知可以在方法出现异常之前之后都进行增强（打印）
    /**
     * description
     * @param joinPoint 参数，环绕通知需要参数 获取到对应增强方法的请求参数  joinPoint就相当于被增强方法封装的对象
     * @return
     */
    @Around("pt()") // 参数，说明具体使用的切点是哪一个
    public Object printLog (ProceedingJoinPoint joinPoint) throws Throwable { // 这里做了 异常声明抛出
        Object ret;// 相当于目标方法的调用--- 目标方法执行之后的返回值
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator()); // log使用了lombok ---代码执行：拼接了系统的换行符
        }
        return ret;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        // 获取当前线程的请求对象 ---springboot封装好了
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();// 注：类名带有Holder，这种都是使用xxx进行数据的共享，xxx保证资源的隔离，避免多线程并发的问题
        HttpServletRequest request = requestAttributes.getRequest();// url就在request中

        // 获取被增强方法上的注解对象 --- 需要将被增强方法的信息传到方法里
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL()); // 获取当前请求的url
        // 打印描述信息
        log.info("BusinessName   : {}", systemLog.businessName()); // 还是挺绕的
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature) joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private void handleAfter(Object ret) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(ret));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);// methodSignature.getMethod()就是获取添加SystemLog的方法，（被封装成了一个对象）
        return systemLog;
    }
}
