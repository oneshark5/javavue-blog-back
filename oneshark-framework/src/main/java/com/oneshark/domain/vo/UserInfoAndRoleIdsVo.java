package com.oneshark.domain.vo;

import com.oneshark.domain.entity.Role;
import com.oneshark.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/3/1 16:26
 * @注释 把指定字段返回给前端
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAndRoleIdsVo {
    private User user;
    private List<Role> roles;
    private List<Long> roleIds;
}
