package com.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * 初始化一个固定的sqlSessionFactory
 */
public class SqlSessionFactoryUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //加载mybatis配置文件
            InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
            //创建一个sqlSessionFactory并赋值
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
