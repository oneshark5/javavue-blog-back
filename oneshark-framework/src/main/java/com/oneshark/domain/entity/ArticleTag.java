package com.oneshark.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/18 16:43
 * @注释 文章表&标签表的中间表，对应的实体类
 */
@TableName(value="sg_article_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag {
    private static final long serialVersionUID = 625337492348897098L;

    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 标签id
     */
    private Long tagId;
}
