package com.li.mapper;

import com.li.pojo.User;
import com.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class TestUserMapper {

    @Test
    public void testSelectUser() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUser("li", "123");

        sqlSession.close();

        System.out.println(user);
    }

    @Test
    public void testInsertUser() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int count = userMapper.insertUser(new User("wang", "999"));

        sqlSession.commit();

        sqlSession.close();

        System.out.println(count);
    }
}
