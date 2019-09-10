package com.longdatech.mybatisdo;

import com.longdatech.mybatisdo.dao.BlogMapper;
import com.longdatech.mybatisdo.po.Blog;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class DemoTest {

    static SqlSessionFactory sqlSessionFactory;
    static BlogMapper blogMapper;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            blogMapper = sqlSession.getMapper(BlogMapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        int num = blogMapper.count();
        System.out.println(num);
    }

    @Test
    public void test2() {
        List<Blog> blog = blogMapper.selectById(1);
        System.out.println(blog);
    }

    @Test
    public void test3() throws Exception {
        Reader reader = new FileReader("E:\\C\\mybatisdo\\src\\main\\resources\\mybatis-config.xml");
        Properties properties = new Properties();
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/mybatisdo?characterEncoding=utf-8");
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, properties);
        SqlSession sqlSession = factory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        System.out.println(mapper);
        mapper.selectById(1);
//        System.out.println(mapper.count());
    }

    @Test
    public void test4() throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("src/main/resources/db.properties");
        properties.load(inputStream);
        Set set = properties.stringPropertyNames();
        set.forEach(i -> {
            System.out.println(i);
        });
    }

    @Test
    public void test5() {
        File f = new File("src/main/resources/db.properties");
        System.out.println(f.getAbsolutePath());
    }

    @Test
    public void test6() {
        List<Blog> res = blogMapper.selectById(1);
        System.out.println(res);

        Collection collection;
    }

    @Test
    public void test9() throws Exception {
        long start = System.currentTimeMillis(), end, used;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(2);
        List<Integer> list = new CopyOnWriteArrayList<>();

        Thread t0 = new Thread(() -> {
            System.out.println("开始添加元素");
            for (int i = 0; i < 80000000; i++) {
                list.add(i);
            }
            System.out.println("添加函数结束：size=" + list.size());
            countDownLatch.countDown();
        });
        t0.start();
        countDownLatch.await();

        System.out.println("开始转换为数组");
        Thread t1 = new Thread(() -> {
            System.out.println("线程1开始");
            long s = System.currentTimeMillis(), e, u;
            Integer[] t = new Integer[80000011];
            Integer[] b = list.toArray(t);
            System.out.println("b输出结果：");
            for (int i = 0; i < 10; i++) {
                System.out.print(b[i] + ",");
            }
            System.out.println();
            System.out.println("list输出结果：");
            for (int k = 0; k < 10; k++) {
                System.out.print(list.get(k) + ",");
            }
            e = System.currentTimeMillis();
            u = e - s;
            System.out.println("线程1结束,转换总耗时=" + u + ",b总长度:" + b.length + ",list总长度：" + list.size());
            countDownLatch2.countDown();
        });

        Thread t2 = new Thread(() -> {
            System.out.println("线程2开始");
            try {
                Thread.sleep(50);
                for (int i = 0; i < 10; i++) {
                    list.remove(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程2结束:size:" + list.size());
            countDownLatch2.countDown();
        });

        t1.start();
        t2.start();

        countDownLatch2.await();
        end = System.currentTimeMillis();
        used = end - start;
        System.out.println("最终结果：");
        for (int k = 0; k < 10; k++) {
            System.out.print(list.get(k) + ",");
        }
        System.out.println("used:" + used);
    }

    public enum People {
        CHINESE("中国", 1), JAPAN("日本", 2), AMERICAN("美国", 3);

        private String name;
        private Integer index;

        People(String name, Integer index) {
            this.name = name;
            this.index = index;
        }

        public String getName(int index) {
            for (People p : People.values()) {
                if (p.index == index) {
                    return p.name;
                }
            }
            return "无";
        }

    }

    @Test
    public void test10() throws Exception{
        Blog blog = blogMapper.selectByTitle("测试",1);

        String nameVlues = "";
        Class cls = blog.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i=0;i<fields.length;i++){//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(blog);
                //一个个赋值
                nameVlues += name+":"+value+",";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //获取最后一个逗号的位置
        int lastIndex = nameVlues.lastIndexOf(",");

        //不要最后一个逗号","
        String  result= nameVlues.substring(0,lastIndex);
        System.out.println(result);
    }

    @Test
    public void test12(){
        Blog blog1 = blogMapper.selectByTitle("测试",1);
//        Blog blog2 = blogMapper.selectById(1);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        Blog obj = (Blog) sqlSession.selectOne("com.longdatech.mybatisdo.dao.BlogMapper.selectById", 1);
//        System.out.println(blog2);
//        System.out.println(obj);
        System.out.println(blog1);
        Map map = new HashMap();
        map.put("id",1);
        map.put("title","测试");
//        map.put("param1","测试");
//        map.put("param2",1);
        Blog obj = sqlSession.selectOne("com.longdatech.mybatisdo.dao.BlogMapper.selectByTitle", map);
        System.out.println(obj);
    }

    @Test
    public void test11(){
        List<Blog> list = blogMapper.selectById(1);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Object obj = new Object();

        sqlSession.<Blog>selectList("com.longdatech.mybatisdo.dao.BlogMapper.selectById", obj);
    }

    @Test
    public void test13() throws Exception{
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            blogMapper = sqlSession.getMapper(BlogMapper.class);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test14(){
        Collections.synchronizedMap(new HashMap<>());
    }

    @Test
    public void test(){
        List<Blog> list = blogMapper.selectById(1);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Object obj = new Object();

        sqlSession.<Blog>selectList("com.longdatech.mybatisdo.dao.BlogMapper.selectById", obj);

    }

}
