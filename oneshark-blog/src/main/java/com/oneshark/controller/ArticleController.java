package com.oneshark.controller;

import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.entity.Article;
import com.oneshark.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /*测试配置是否成功*/
    @GetMapping("/lists")
    public List<Article> testDemo() {
        return articleService.list();
    }

    /*热门文章*/
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        ResponseResult result = articleService.hotArticleList(); // 具体的业务逻辑写在service层
        return result;
    }

    /*文章分页查询*/
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    /**
     * description 文章详情接口
     * @param id 文章对应的id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    /**
     * 更新浏览量
     * @param id
     * @return
     */
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id")Long id) {
        return articleService.updateViewCount(id);
    }
}
