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
    public void test4(){
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder.build(reader, environment, props);
//        new SqlSessionFactoryBuilder().build(null,null,null);
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder.build(null, null, null);

    }
    interface People{
        String area = "earth";
    }
    abstract class Chinese{
        String language = "chinese";
    }
    class Person extends Chinese implements People{
        private Integer id;
    }

    @Test
    public void test5(){
        Class c = Person.class;
        Class c2 = c.getSuperclass();
        Type t = c.getGenericSuperclass();

        System.out.println(c);
        System.out.println(c2);
        System.out.println(t);
    }

}
