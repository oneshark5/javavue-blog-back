package com.oneshark.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/18 9:33
 * @注释 修改标签
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditTagDto {
    private Long id;
    //备注
    private String remark;
    //标签名
    private String name;
}
