package com.oneshark.handler.exception;

import com.oneshark.domain.ResponseResult;
import com.oneshark.enums.AppHttpCodeEnum;
import com.oneshark.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice // 对Controller的增强
//@ResponseBody
// 上面两个注解相当于 @RestControllerAdvice
@RestControllerAdvice // 方法的返回值都会转换成json封装在响应体中
@Slf4j
public class GlobalExceptionHandler {
    // 注：第一个异常 是自定义的异常类，第二个异常是除了自定义外的其他异常
    // 异常的处理方法
    @ExceptionHandler(SystemException.class) //对应异常，判断异常处理的字节码对象
    public ResponseResult systemExceptionHandler(SystemException e){
        // 打印异常信息
        log.error("出现了异常！{}", e);
        // 从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }
    // 处理SpringSecurity的权限异常
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handleAccessDeniedException(AccessDeniedException e) {
        return ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH.getCode(),e.getMessage());//枚举值是500
    }
    // 其他异常
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        //打印异常信息
        log.error("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
