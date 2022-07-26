package org.apache.ibatis.example.mapper;

import org.apache.ibatis.example.domain.MyBlog;

/**
 * @auther zgp
 * @desc
 * @date 2022/7/16
 */
public interface MyBlogMapper {
    int updateMyBlog(MyBlog myBlog);
    MyBlog selectMyBlog(MyBlog myBlog);
    MyBlog selectMyBlog1(MyBlog myBlog);
}
