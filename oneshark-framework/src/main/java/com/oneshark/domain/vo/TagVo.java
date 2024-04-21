package com.oneshark.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2023/12/17 1:09
 * @注释
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {
    private Long tagId;
    private String tagName;
    private String tagRemark;
}
