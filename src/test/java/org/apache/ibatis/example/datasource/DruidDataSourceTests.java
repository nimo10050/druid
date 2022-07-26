package org.apache.ibatis.example.datasource;

import org.apache.ibatis.example.domain.MyBlog;
import org.apache.ibatis.example.mapper.MyBlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @auther zgp
 * @desc
 * @date 2022/7/21
 */
public class DruidDataSourceTests {

    private SqlSessionFactory sqlSessionFactory;


    @Before
    public void setUp() throws IOException {
        //org/apache/ibatis/example/datasource/mybatis-config-druid.xml
        String resource = "org/apache/ibatis/example/datasource/mybatis-config-druid.xml";// org/apache/ibatis/org.apache.ibatis.example/mybatis-config.xml
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testSelectMyBlogById() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 thread run start");
            MyBlog myBlog = new MyBlog(1);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            MyBlogMapper mapper = sqlSession.getMapper(MyBlogMapper.class);
            MyBlog getOne = mapper.selectMyBlog(myBlog);
            Assert.assertTrue(getOne != null);
            System.out.println("thread1 thread run end");
        }, "thread1");


        Thread thread2 = new Thread(() -> {

            System.out.println("thread2 thread run start");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            MyBlog myBlog1 = new MyBlog(2);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            MyBlogMapper mapper = sqlSession.getMapper(MyBlogMapper.class);
            MyBlog getOne1 = mapper.selectMyBlog1(myBlog1);
            Assert.assertTrue(getOne1 == null);
            System.out.println("thread2 thread run end");
        }, "thread2");

        thread1.start();
        thread2.start();

        System.out.println("all thread is running");
        Thread.sleep(200000);
    }
}
