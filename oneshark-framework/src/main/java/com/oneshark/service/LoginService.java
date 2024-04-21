package com.oneshark.service;

import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
