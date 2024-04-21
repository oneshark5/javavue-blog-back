package com.oneshark.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/1/18 17:18
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTagDto {
    //备注
    private String remark;
    //标签名
    private String name;
}
