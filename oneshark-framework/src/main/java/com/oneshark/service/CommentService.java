package com.oneshark.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-11-14 10:55:24
 */
public interface CommentService extends IService<Comment> {
    ResponseResult commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

