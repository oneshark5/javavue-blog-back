package com.oneshark.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/19 16:57
 * @注释 用于接收前端传来的参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    //分类名
    private String name;
    //描述
    private String description;
    //状态0:正常,1禁用
    private String status;
}
