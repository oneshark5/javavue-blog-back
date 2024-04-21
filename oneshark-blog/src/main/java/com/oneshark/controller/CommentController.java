package com.oneshark.controller;

import com.oneshark.constants.SystemConstants;
import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.dto.AddCommentDto;
import com.oneshark.domain.entity.Comment;
import com.oneshark.service.CommentService;
import com.oneshark.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "Comment Controller 评论",description = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @GetMapping("/linkCommentList")
    // 接口参数描述
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    }
    )
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }

    /**
     * 发表评论
     * @param addCommentDto （说明：向RequestBody中获取请求参数）
     * @return
     */
    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto) {
        // 将AddCommentDto转换成Comment再传过去
        // 方便维护，该接口参数直接改实体类就行，不需要动代码
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class); // 该方法还是要看啊  小伙子
        return commentService.addComment(comment);// 将数据添加到数据库
    }
}
