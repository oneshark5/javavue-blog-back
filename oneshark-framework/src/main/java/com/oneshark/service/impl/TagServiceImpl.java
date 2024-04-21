package com.oneshark.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oneshark.domain.ResponseResult;
import com.oneshark.domain.dto.TabListDto;
import com.oneshark.domain.dto.TagListDto;
import com.oneshark.domain.entity.LoginUser;
import com.oneshark.domain.entity.Tag;
import com.oneshark.domain.vo.PageVo;
import com.oneshark.domain.vo.TagVo;
import com.oneshark.mapper.TagMapper;
import com.oneshark.service.TagService;
import com.oneshark.utils.BeanCopyUtils;
import com.oneshark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-11-27 21:20:45
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    // 标签查询
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        /**
         * 传三个参数 布尔值（判断值是否为null）、查询的字段名、值；如果第一个参数为false，后面就不会再判断
         * StringUtils.hasText()方法，会判断是否为null和值是否大于0.
         * 其中参数二三 二表示查询到的字段、三表示定义在dto中的字段
         */
        // 第二、三个参数是互相比较。第一个参数是判空，当用户没有查询条件时，就不去比较后面两个参数
        // 采用模糊查询
        queryWrapper.like(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    //-------------------------------新增标签------------------------------------
    @Override
    public ResponseResult addTag(TabListDto tagListDto) {
        Tag tag = new Tag();
        //获取创建人、创建时间
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //获取创建人的id
        tag.setCreateBy(loginUser.getUser().getId());

        try{
            //生成创建时间、更新时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取当前时间
            Date now = new Date();
            //将当前时间格式化为指定格式的字符串
            String strNow = dateFormat.format(now);
            //将字符串转换为Date类型
            Date date = dateFormat.parse(strNow);
            //最终得到的就是创建时间
            tag.setCreateTime(date);
        } catch (Exception e){
            e.printStackTrace();
        }

        //修改标签名、标签的描述信息
        tag.setName(tagListDto.getName());
        tag.setRemark(tagListDto.getRemark());

        //把新增好后的数据插入数据库
        tagMapper.insert(tag);
        return ResponseResult.okResult();
    }

    //-------------------------------删除标签------------------------------------
    @Override
    public ResponseResult deleteTag(Long id) {
        // 通过数据id查找数据
        Tag tag  = tagMapper.selectById(id);
        // 把值传入数据库进行更新
        // if (tag != null){
        //     // 把 def_flag=1 为逻辑删除
        //     int flag = 1;
        //     tagMapper.myUpdateById(id,flag);
        // }
        tagMapper.deleteById(id); // 这里直接逻辑删除
        return ResponseResult.okResult();
    }
    //-------------------------------修改标签------------------------------------
    //①根据标签的id来查询标签
    @Override
    public ResponseResult getLableById(Long id) {
        Tag tag = tagMapper.selectById(id);
        // 封装成vo响应给前端
        TagVo tagVoData = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVoData);
    }

    @Override
    public ResponseResult myUpdateById(TagVo tagVo) {
        Tag tag = new Tag();
        // 获取更新时间、更新人
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 更新人的id
        tag.setUpdateBy(loginUser.getUser().getId());

        // 创建时间、更新时间
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 获取当前时间
            Date now = new Date();
            //将当前时间格式化为指定格式的字符串
            String strNow = dateFormat.format(now);
            //将字符串转换为Date类型
            Date date = dateFormat.parse(strNow);
            //最终得到的就是创建时间
            tag.setUpdateTime(date);
        } catch (Exception e){
            e.printStackTrace();
        }

        //修改标签id、标签名、标签的描述信息
        tag.setId(tagVo.getTagId());
        tag.setName(tagVo.getTagName());
        tag.setRemark(tagVo.getTagRemark());

        //把新增好后的数据插入数据库
        tagMapper.updateById(tag);
        return ResponseResult.okResult();
    }

    //---------------------------写博文-查询文章标签的接口---------------------------
    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper();
        wrapper.select(Tag::getId, Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }


    // @Override
    // public ResponseResult addTag(String name, String remark) {
    //     // String name = tag.getName();
    //     // String remark = tag.getRemark();
    //     // 标签是否存在(查询数据表，采用spring的断言判断)
    //     /*常规写法*/
    //     LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
    //     queryWrapper.select(Tag::getId);
    //     queryWrapper.eq(Tag::getName, name);
    //     Tag existTag = tagMapper.selectOne(queryWrapper);
    //     Assert.isNull(existTag, name + "标签已存在");
    //     // 添加新标签
    //     Tag newTag = new Tag();
    //     newTag.setName(name);
    //     newTag.setRemark(remark);
    //     tagMapper.insert(newTag);
    //
    //     //
    //     // /*Stream流的写法*/
    //     // Tag existTag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
    //     //         .select(Tag::getId)
    //     //         .eq(Tag::getName, name));
    //     // Assert.isNull(existTag, name + "标签已存在");
    //     // Tag newTag = Tag.builder()
    //     //         .name(name)
    //     //         .remark(remark)
    //     //         .build();
    //     // tagMapper.insert(newTag);// tagMapper继承baseMapper；效果等同； 使用时还是要使用对应的Mapper ---tagMapper
    //     return ResponseResult.okResult();
    // }

    // @Override
    // public ResponseResult<TagVo> getTagById(Long tagId) {
    //     // 查询tag
    //     Tag tag = tagMapper.selectById(tagId);
    //     // 封装返回
    //     TagVo tagVo = new TagVo(tag.getId(), tag.getName(), tag.getRemark());
    //     return ResponseResult.okResult(tagVo);
    // }
    //
    // @Override
    // public ResponseResult updateTag(Tag tag) {
    //     tagMapper.updateById(tag);
    //     return ResponseResult.okResult();
    // }

    // 原来的写法
    // @Override
    // public ResponseResult updateTag(TagVo tagVo) {
    //     // 标签是否存在
    //     /*常规写法*/
    //     /*LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
    //     queryWrapper.select(Tag::getId); // 筛选id的数据
    //     queryWrapper.eq(Tag::getId, tagVo.getTagId());
    //     Tag existTag  = tagMapper.selectOne(queryWrapper);
    //     // 使用log去打印日志
    //     log.error(existTag + "标签已存在");*/
    //
    //     /*简单写法*/
    //     Tag existTag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
    //             .select(Tag::getId, Tag::getName)
    //             .eq(Tag::getName, tagVo.getTagName()));
    //     Assert.isTrue(ObjectUtils.isEmpty(existTag), "标签已存在");
    //     // 修改标签（正常的写法是创建一个对象，然后设置参数，将对象封装返回）
    //     // 这里使用@Builder创建者模式去实现
    //     Tag newTag = Tag.builder()
    //             .id(tagVo.getTagId())
    //             .name(tagVo.getTagName())
    //             .remark(tagVo.getTagRemark())
    //             .build();
    //     baseMapper.updateById(newTag);
    //     return ResponseResult.okResult();
    // }


    // @Override
    // public ResponseResult removeById(Long id) {
    //     tagMapper.deleteById(id);
    //     return ResponseResult.okResult();
    // }

}

