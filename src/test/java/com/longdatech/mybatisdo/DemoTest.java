package com.longdatech.mybatisdo;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.longdatech.mybatisdo.dao.BlogMapper;
import com.longdatech.mybatisdo.po.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collection;

public class DemoTest {

    static String resource = "mybatis-config.xml";
    static BlogMapper blogMapper = null;

    static {
        try{
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            blogMapper = sqlSession.getMapper(BlogMapper.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlog(1);
        System.out.println(blog);
    }

    @Test
    public void test2() throws Exception{
        String resource = "mybatis-config.xml";
        URL url = Resources.getResourceURL(resource);
        System.out.println(url);
        Object obj = url.getContent();
        System.out.println(obj);
    }

    @Test
    public void test3(){
        Collection collection;

        int i = blogMapper.countAll();
        System.out.println("总记录数:" + i);

    }

    @Test
    public void test(){
        Integer[] a = new Integer[2];
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer[] b = list.toArray(a);
        System.out.println(a);
        System.out.println(b);
    @Test
    public void test4(){
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder.build(reader, environment, props);
//        new SqlSessionFactoryBuilder().build(null,null,null);
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder.build(null, null, null);

    }
}
