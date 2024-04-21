package com.oneshark.controller;

import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.dto.ChangeRoleStatusDto;
import com.oneshark.domain.entity.Role;
import com.oneshark.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/29 14:14
 * @注释
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //------------------------------查询角色列表---------------------------------------
    @GetMapping("/list")
    public ResponseResult list(Role role, Integer pageNum, Integer pageSize){
        return roleService.selectRolePage(role, pageNum, pageSize);
    }

    //-----------------------------修改角色的状态--------------------------------------
    /**
     *
     * @param roleStatusDto 传的参数是一个dto对象，获取对象的属性（字段）直接get就行
     * @return
     */
    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeRoleStatusDto roleStatusDto){
        Role role = new Role();
        role.setId(roleStatusDto.getRoleId());
        role.setStatus(roleStatusDto.getStatus());
        return ResponseResult.okResult(roleService.updateById(role));
    }
    //-------------------------------新增角色-----------------------------------------
    @PostMapping
    public ResponseResult add( @RequestBody Role role) {
        roleService.insertRole(role);
        return ResponseResult.okResult();
    }
    //----------------------修改角色-根据角色id查询对应的角色-----------------------------
    @GetMapping(value = "/{roleId}")
    public ResponseResult getInfo(@PathVariable Long roleId) {
        Role role = roleService.getById(roleId);
        return ResponseResult.okResult(role);
    }

    //-------------------------修改角色-保存修改好的角色信息------------------------------

    @PutMapping
    public ResponseResult edit(@RequestBody Role role) {
        roleService.updateRole(role);
        return ResponseResult.okResult();
    }
    //--------------------------------删除角色---------------------------------------
    @DeleteMapping("/{id}")
    public ResponseResult remove (@PathVariable(value = "id") Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }
    //--------------------------------新增用户---------------------------------------
    @GetMapping("/listAllRole")
    //①查询角色列表接口
    public ResponseResult listAllRole(){
        return ResponseResult.okResult(roleService.selectRoleAll());
    }
}
