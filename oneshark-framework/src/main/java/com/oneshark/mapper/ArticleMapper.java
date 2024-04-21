package com.oneshark.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oneshark.domain.entity.Article;

/**
 * 操作数据库的关键在于继承 BaseMapper mybatisplus封装了操作数据库的方法
 */
public interface ArticleMapper extends BaseMapper<Article> {
}
