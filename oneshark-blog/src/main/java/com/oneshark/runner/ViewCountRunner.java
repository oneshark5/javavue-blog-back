package com.oneshark.runner;

import com.oneshark.domain.entity.Article;
import com.oneshark.mapper.ArticleMapper;
import com.oneshark.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 在应用启动时把博客的浏览量存储到redis中
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache; // redis的处理方法

    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null); // 会将博客的全部信息都查询过来
        // 采用stream流去处理
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue();//Collectors.toMap两个参数，即如何获取key和如何获取value
                }));
        // 存储到redis中
        redisCache.setCacheMap("article:viewCount", viewCountMap);
    }
}
