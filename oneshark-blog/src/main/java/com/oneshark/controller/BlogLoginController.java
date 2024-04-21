package com.oneshark.controller;

import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.entity.User;
import com.oneshark.enums.AppHttpCodeEnum;
import com.oneshark.exception.SystemException;
import com.oneshark.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    // 参数后续可以使用dto
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        // 对请求参数进行合法性的判断
        if(!StringUtils.hasText(user.getUserName())){// 不存在or为null
            // 提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }

        return blogLoginService.login(user);
    }

    // 退出登录
    @PostMapping("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}
