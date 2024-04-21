package com.oneshark.controller;

import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.dto.AddTagDto;
import com.oneshark.domain.dto.TagListDto;
import com.oneshark.domain.entity.Tag;
import com.oneshark.domain.vo.PageVo;
import com.oneshark.domain.vo.TagVo;
import com.oneshark.service.TagService;
import com.oneshark.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    //-------------------------------查询标签------------------------------------
    /**
     * description 查询标签列表
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param tagListDto 设置查询筛选数据的条件（当没有值时会全部返回，定义值后只返回符合条件的数据）
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    //-------------------------------新增标签------------------------------------
    /**
     * description 新增标签
     * @return
     */
    @PostMapping
    public ResponseResult add (@RequestBody AddTagDto tagDto) {
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    //-------------------------------删除标签------------------------------------
    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id){
        // tagService.removeById(id);
        // return ResponseResult.okResult();
        return tagService.deleteTag(id);
    }

    //-------------------------------修改标签------------------------------------
    /**
     * 修改标签
     * @param id
     * @return
     */
    //①根据标签id查询标签
    @GetMapping("/{id}")
    public ResponseResult getLableById(@PathVariable Long id){
        return tagService.getLableById(id);
    }
    //②根据标签的id来修改标签
    @PutMapping
    public ResponseResult updateById(@RequestBody TagVo tagVo){
        return tagService.myUpdateById(tagVo);
    }

    //---------------------------写博文-查询文章标签的接口---------------------------
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
