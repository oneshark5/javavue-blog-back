package com.oneshark.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oneshark.constants.SystemConstants;
import com.oneshark.domain.entity.LoginUser;
import com.oneshark.domain.entity.User;
import com.oneshark.mapper.MenuMapper;
import com.oneshark.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    // 相当于UserDetailServiceImpl将用户名和密码传过来
    // 查询采用的mybatisPlus，还不如直接操作数据库。。。
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        // 判断是否查到用户，如果没有查到就抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        // 如果是后台用户，才需要查询权限，也就是只对后台用户做权限校验
        if(user.getType().equals(SystemConstants.IS_ADMAIN)){
            //根据用户id查询权限关键字，即list是权限信息的集合
            // List<String> list = menuMapper.selectPermsByOtherUserId(user.getId());
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,list);
        }
        // 返回用户信息---查到，就作为方法的返回值返回
        // 如果不是后台用户，就只封装用户信息，不封装权限信息
        //返回查询到的用户信息。注意下面那行直接返回user会报错，我们需要在huanf-framework工程的domain目录新
        //建LoginUser类，在LoginUser类实现UserDetails接口，然后下面那行就返回LoginUser对象
        //TODO 查询权限信息封装
        return new LoginUser(user, null); // 把对应的用户信息传到LoginUser里面
    }
}
