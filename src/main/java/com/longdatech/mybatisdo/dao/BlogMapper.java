package com.longdatech.mybatisdo.dao;

import com.longdatech.mybatisdo.po.Blog;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {

    Blog selectBlog(Integer id);

    @Select("SELECT COUNT(*) FROM blog")
    int countAll();
}
