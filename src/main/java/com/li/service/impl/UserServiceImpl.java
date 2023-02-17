package com.li.service.impl;

import com.li.mapper.UserMapper;
import com.li.pojo.User;
import com.li.service.UserService;
import com.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {

    public User login(String userName, String password) {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUser(userName, password);

        sqlSession.close();

        return user;
    }

    public int register(User user) {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int count = userMapper.insertUser(user);

        sqlSession.commit();

        sqlSession.close();

        return count;
    }
}
