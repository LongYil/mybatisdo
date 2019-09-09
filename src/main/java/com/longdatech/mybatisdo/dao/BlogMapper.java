package com.longdatech.mybatisdo.dao;

import com.longdatech.mybatisdo.po.Blog;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {

    int count();

    @Select(value = "SELECT * FROM blog WHERE id = #{id}")
    Blog selectById(Integer id);


}
