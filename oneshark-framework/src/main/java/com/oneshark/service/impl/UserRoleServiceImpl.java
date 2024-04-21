package com.oneshark.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oneshark.domain.entity.UserRole;
import com.oneshark.mapper.UserRoleMapper;
import com.oneshark.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-03-01 14:40:16
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

