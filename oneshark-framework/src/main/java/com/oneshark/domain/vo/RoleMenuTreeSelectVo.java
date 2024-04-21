package com.oneshark.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/29 15:53
 * @注释 把指定字段返回给前端
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuTreeSelectVo {
    private List<Long> checkedKeys;
    private List<MenuTreeVo> menus;
}
