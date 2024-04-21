package com.oneshark.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oneshark.domain.entity.Tag;
import org.springframework.stereotype.Component;


/**
 * 标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-27 21:20:43
 */
@Component
public interface TagMapper extends BaseMapper<Tag> {
    // 删除标签 --- 逻辑删除
    // int myUpdateById(@Param("id") Long id, @Param("flag") int flag);
}

