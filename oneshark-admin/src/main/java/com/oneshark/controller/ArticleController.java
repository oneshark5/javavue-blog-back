package com.oneshark.controller;

import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.dto.AddArticleDto;
import com.oneshark.domain.dto.ArticleDto;
import com.oneshark.domain.entity.Article;
import com.oneshark.domain.vo.ArticleByIdVo;
import com.oneshark.domain.vo.PageVo;
import com.oneshark.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/18 17:16
 * @注释
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    //------------------------------新增博客文章-----------------------------
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    //-----------------------------分页查询博客文章---------------------------
    @GetMapping("/list")
    public ResponseResult list(Article article, Integer pageNum, Integer pageSize) {
        PageVo pageVo = articleService.selectArticlePage(article, pageNum, pageSize);
        return ResponseResult.okResult();
    }
    //---------------------------根据文章id来修改文章--------------------------
    // 先查询再修改
    //①先查询根据文章id查询对应的文章
    @GetMapping(value = "/{id}")
    public ResponseResult<ArticleByIdVo> getInfo(@PathVariable(value = "id") Long id){
        ArticleByIdVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }
    //②然后才是修改文章
    @PutMapping
    public ResponseResult edit(@RequestBody ArticleDto articleDto){
        articleService.edit(articleDto);
        return ResponseResult.okResult();
    }
    //---------------------------根据文章id来删除文章-------------------------
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        //直接使用mybatisplus提供的removeById方法
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}
