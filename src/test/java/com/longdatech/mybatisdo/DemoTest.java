package com.longdatech.mybatisdo;

import com.longdatech.mybatisdo.dao.BlogMapper;
import com.longdatech.mybatisdo.po.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class DemoTest {

    static SqlSessionFactory sqlSessionFactory;
    static BlogMapper blogMapper;
    static {
        try{
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        int num = blogMapper.count();
        System.out.println(num);
    }

    @Test
    public void test2(){
        Blog blog = blogMapper.selectById(1);
        System.out.println(blog);
    }

    @Test
    public void test3() throws Exception{
        Reader reader = new FileReader("E:\\C\\mybatisdo\\src\\main\\resources\\mybatis-config.xml");
        Properties properties = new Properties();
        properties.setProperty("username","root");
        properties.setProperty("password","root");
        properties.setProperty("url","jdbc:mysql://localhost:3306/mybatisdo?characterEncoding=utf-8");
        properties.setProperty("driver","com.mysql.jdbc.Driver");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, properties);
        SqlSession sqlSession = factory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        System.out.println(mapper);
        mapper.selectById(1);
//        System.out.println(mapper.count());
    }

    @Test
    public void test4() throws Exception{
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("src/main/resources/db.properties");
        properties.load(inputStream);
        Set set = properties.stringPropertyNames();
        set.forEach(i->{
            System.out.println(i);
        });
    }

    @Test
    public void test5(){
        File f = new File("src/main/resources/db.properties");
        System.out.println(f.getAbsolutePath());
    }

    @Test
    public void test6(){
        Blog res = blogMapper.selectById(1);
        System.out.println(res);

        Collection collection;
    }
}
