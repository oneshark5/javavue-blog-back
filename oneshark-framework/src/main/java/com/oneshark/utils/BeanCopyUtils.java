package com.oneshark.utils;

import com.oneshark.domain.entity.Article;
import com.oneshark.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    // 单个实体类的copy

    /**
     *
     * @param source 待复制的内容
     * @param clazz 字节码
     * @return
     * @param <V>
     */
    public static <V>V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();// 创建空对象
            // 实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 列表的copy --- 流的写法
    public static <O, V>List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }


    // 测试copy方法是否可行
    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("ss");

        //进行复制
        HotArticleVo hotArticleVo = copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo);
    }

}
