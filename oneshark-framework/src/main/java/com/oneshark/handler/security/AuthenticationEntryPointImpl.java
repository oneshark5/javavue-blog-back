package com.oneshark.handler.security;

import com.alibaba.fastjson.JSON;
import com.oneshark.domain.ResponseResult;
import com.oneshark.enums.AppHttpCodeEnum;
import com.oneshark.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        // 相应格式满足接口规范  ResponseResult
        ResponseResult result = null;
        if(authException instanceof BadCredentialsException) {
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
        }
        // 响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}
