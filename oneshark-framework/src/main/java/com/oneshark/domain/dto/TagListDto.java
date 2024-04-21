package com.oneshark.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询标签的请求参数dto") //这个是关于swagger的注解
public class TagListDto {
    private Long id;
    private String name; // 标签名
    private String remark; // 备注
}
