package com.oneshark.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oneshark.domain.vo.ArticleVo;
import com.oneshark.mapper.ArticleVoMapper;
import com.oneshark.service.ArticleVoService;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/18 17:05
 * @注释
 */
@Service
public class ArticleVoServiceImpl extends ServiceImpl<ArticleVoMapper, ArticleVo> implements ArticleVoService {
}
