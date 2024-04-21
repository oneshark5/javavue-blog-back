package com.oneshark.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/18 10:29
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增标签的请求参数dto") //这个是关于swagger的注解
public class TabListDto {
    //请求参数。用户可传可不传，这两个参数是给用户在搜索框根据name查询对应的标签，或根据remark查询对应的标签
    private String name;
    private String remark;
}
