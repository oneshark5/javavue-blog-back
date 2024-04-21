package com.oneshark.controller;

import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.entity.Link;
import com.oneshark.domain.vo.PageVo;
import com.oneshark.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/3/12 15:26
 * @注释
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;
    //-----------------------------分页查询友链---------------------------------
    @GetMapping("/list")
    public ResponseResult link (Link link, Integer pageNum, Integer pageSize) {
        PageVo pageVo = linkService.selectLinkPage(link,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }
    //-------------------------------增加友链----------------------------------
    public ResponseResult add (@RequestBody Link link) {
        linkService.save(link);
        return ResponseResult.okResult();
    }
    //-------------------------------修改友链---------------------------------
    //①根据id查询友链
    @GetMapping("/{id}")
    public ResponseResult getInfo (@PathVariable(value = "id") Long id){
        Link link = linkService.getById(id);
        return ResponseResult.okResult(link);
    }
    //②修改友链状态
    @PutMapping("/changeLinkStatus")
    public ResponseResult changeLinkStatus (@RequestBody Link link) {
        linkService.updateById(link);
        return ResponseResult.okResult();
    }
    //③修改友链
    @PutMapping
    public ResponseResult edit(@RequestBody Link link) {
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    //-------------------------------删除友链---------------------------------
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        linkService.removeById(id);
        return ResponseResult.okResult();
    }
}
