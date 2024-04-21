package com.oneshark.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.dto.TabListDto;
import com.oneshark.domain.dto.TagListDto;
import com.oneshark.domain.entity.Tag;
import com.oneshark.domain.vo.PageVo;
import com.oneshark.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-11-27 21:20:45
 */
public interface TagService extends IService<Tag> {

    // 查询标签
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
    //新增标签
    ResponseResult addTag(TabListDto tagListDto);
    //删除标签
    ResponseResult deleteTag(Long id);
    //修改标签-①根据标签的id来查询标签
    ResponseResult getLableById(Long id);
    //修改标签-②根据标签的id来修改标签
    ResponseResult myUpdateById(TagVo tagVo);

    //写博文-查询文章标签的接口
    List<TagVo> listAllTag();
}

