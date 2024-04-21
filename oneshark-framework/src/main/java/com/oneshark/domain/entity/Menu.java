package com.oneshark.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author makejava
 * @since 2023-11-29 17:10:10
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
@Accessors(chain = true) // 特点：加上这个注解，set方法返回一个Menu，不加返回的就是void
public class Menu  {
    //菜单ID
    @TableId
    private Long id;

    //菜单名称
    private String menuName;
    //父菜单ID
    private Long parentId;
    //显示顺序
    private Integer orderNum;
    //路由地址
    private String path;
    //组件路径
    private String component;
    //是否为外链（0是 1否）
    private Integer isFrame;
    //菜单类型（M目录 C菜单 F按钮）
    private String menuType;
    //菜单状态（0显示 1隐藏）
    private String visible;
    //菜单状态（0正常 1停用）
    private String status;
    //权限标识
    private String perms;
    //菜单图标
    private String icon;
    // mybatisplus的字段自增
    //创建者
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新者
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;
    //更新时间
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    //备注
    private String remark;
    
    private String delFlag;
    /**
     * list查询时根据实体类去查询的，多了一个children变量，但是查数据库的时候没有，所以会报错
     * mybatisPlus提供了一个注解@TableField(exist = false) 说明数据表里是不存在，查询的时候不要查这个字段
     */
    //由于数据库没有children字段，所以我们要添加@TableField(exist = false)注解让mybatis在查表时不查询这个字段
    @TableField(exist = false)
    private List<Menu> children; // 从数据库查询出来是没有值的，但接口返回需要有值的，要把对应的子菜单放在父菜单中
}