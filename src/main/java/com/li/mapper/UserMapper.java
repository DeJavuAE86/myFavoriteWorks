package com.li.mapper;

import com.li.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectUser(@Param("userName") String userName, @Param("password") String password);

    int insertUser(User user);
}
