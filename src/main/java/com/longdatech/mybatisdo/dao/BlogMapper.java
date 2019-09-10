package com.longdatech.mybatisdo.dao;

import com.longdatech.mybatisdo.po.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {

    int count();

    List<Blog> selectById(Integer id);

    Blog selectById2(Integer id);

    Blog selectByTitle(@Param("title") String title,@Param("id") Integer id);
}
