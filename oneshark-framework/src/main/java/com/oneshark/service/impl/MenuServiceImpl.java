package com.oneshark.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oneshark.constants.SystemConstants;
import com.oneshark.domain.entity.Menu;
import com.oneshark.mapper.MenuMapper;
import com.oneshark.service.MenuService;
import com.oneshark.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-11-29 17:10:11
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限 --- 如果是超级管理员admin的，符合以下条件的，所有的menu对应的perms字段收集成一个集合返回；
        if(id == 1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType,SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL); // 状态为正常的
            List<Menu> menus = list(wrapper);
            // 我们需要的是字符串的List集合
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList()); // .map表示转换，.collect表示收集成集合。
            return perms;
        }
        //否则查询用户所具有的角色信息 --- 如果不是admin，查询这个用户所具有的权限信息。
        // 方法逻辑：先查user_role表 根据用户id(user_id)查角色id(role_id)，查到角色id，再查sys_role_menu表 根据角色id查menu_id，然后拿着menu_id，获取menu信息
        // 对应查询表关系:sys_user_role --> sys_role_menu --> sys_menu（用户--》角色--》菜单）
        return getBaseMapper().selectPermsByUserId(id); // getBaseMapper() 根据用户id查询权限关键字； 根据user_id查role_id
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        // 判断是否是管理员
        if (SecurityUtils.isAdmin()) {
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu(); // 查询肯定要mapper去实现
        }else {
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        // 构建tree
        // 先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L); // 查询出来再封装为tree
        return menuTree;
    }

    //---------------------------------查询菜单列表--------------------------------------
    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper();
        //menuName模糊查询
        queryWrapper.like(StringUtils.hasText(menu.getMenuName()), Menu::getMenuName, menu.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menu.getStatus()), Menu::getStatus, menu.getStatus());
        //排序 parent_id和order_num
        queryWrapper.orderByAsc(Menu::getParentId, Menu::getOrderNum);
        List<Menu> menus = list(queryWrapper);
        return menus;
    }

    //-------------------------------删除菜单-是否存在子菜单-------------------------------
    @Override
    public boolean hasChild(Long MenuId) {
        // 查询是否有父id就可以判断是否存在子菜单 --- 记得加泛型
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Menu::getParentId, MenuId);
        return count(queryWrapper) != 0;
    }

    //--------------------------修改角色-根据角色id查询对应角色菜单列表树---------------------
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return getBaseMapper().selectMenuListByRoleId(roleId);
    }

    /**
     * 找到每一个父menu对应的子menu，放到集合中放到children字段
     * @param menus
     * @param parentId
     * @return
     */
    private List<Menu> builderMenuTree(List<Menu> menus, long parentId) {
        // 集合的过滤处理
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId)) // parentId为0 的
                .map(menu -> menu.setChildren(getChildren(menu, menus)))// set的内容为子menu
                .collect(Collectors.toList());// setChildren是没有返回值的，会报错，采用lombok把menu改成链式编程
        return menuTree;
    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus))) // 如果大于两层
                .collect(Collectors.toList());
        return childrenList;
    }
}

