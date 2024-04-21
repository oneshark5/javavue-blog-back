package com.oneshark.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oneshark.domain.entity.ArticleTag;
import com.oneshark.mapper.ArticleTagMapper;
import com.oneshark.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/18 17:08
 * @注释
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
}
