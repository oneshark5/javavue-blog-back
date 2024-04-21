package com.oneshark.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.entity.Link;
import com.oneshark.domain.vo.PageVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-11-01 16:39:21
 */
public interface LinkService extends IService<Link> {
    // 查询友链
    ResponseResult getAllLink();

    // 分页查询友链
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);

}

