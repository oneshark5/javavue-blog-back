package com.oneshark.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oneshark.domain.entity.User;
import org.springframework.stereotype.Service;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-01 17:08:22
 */
@Service
public interface UserMapper extends BaseMapper<User> {

}

