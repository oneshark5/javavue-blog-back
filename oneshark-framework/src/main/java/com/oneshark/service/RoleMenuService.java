package com.oneshark.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oneshark.domain.entity.RoleMenu;


/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2024-02-29 15:10:16
 */
public interface RoleMenuService extends IService<RoleMenu> {
    //修改角色-保存修改好的角色信息
    void deleteRoleMenuByRoleId(Long id);
}

