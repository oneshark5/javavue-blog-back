package com.oneshark.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oneshark.constants.SystemConstants;
import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.entity.Comment;
import com.oneshark.domain.vo.CommentVo;
import com.oneshark.domain.vo.PageVo;
import com.oneshark.enums.AppHttpCodeEnum;
import com.oneshark.exception.SystemException;
import com.oneshark.mapper.CommentMapper;
import com.oneshark.service.CommentService;
import com.oneshark.service.UserService;
import com.oneshark.utils.BeanCopyUtils;
import com.oneshark.utils.SecurityUtils;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 * onshark：
 * @author makejava
 * @since 2023-11-14 10:55:25
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;
    @Override
    public ResponseResult commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize){
        //查询对应文章(拿到文章id)的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //查询对应文章的根评论（对articleId进行判断）
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId); // 怎么实现的查询
        //根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId, -1); //注：这里的-1不应该写成字面量，应该写成一个常量
        //评论类型
        queryWrapper.eq(Comment::getType, commentType);
        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);
        /*因为自己定义的多了一个字段*/
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        /*查询所有根评论对应的子评论集合，并且赋值给对应的属性 --- 这里使用函数式编程 string流更加简洁*/
        for(CommentVo commentVo : commentVoList) {
            // 查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children); // 设置新值
        }
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //获取当前用户id
        comment.setCreateBy(SecurityUtils.getUserId());
        // 评论内容不能为空 ---
        if(!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);// mybatisplus实现了操作数据库的方法，直接调用即可
        return ResponseResult.okResult();
    }

    // 问题：CommentVo中，手动添加的字段 没有
    // 解决方法，自定义一个集合，查询后再设置到集合返回；---实现Comment到CommentVo的转换
    private List<CommentVo> toCommentVoList(List<Comment> list) {
        // 复制集合CommentVo --- 浅拷贝还是深拷贝？怎么拷贝？
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        // 遍历vo集合
        for (CommentVo commentVo : commentVos) {
            // 通过creatyBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询  ---所回复的目标评论的userid
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }

    /**
     * 根据 根评论的id 查询所对应的 子评论的集合
     * @param id 根评论的id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);// ???
        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }
}

