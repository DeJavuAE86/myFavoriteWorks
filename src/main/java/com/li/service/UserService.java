package com.li.service;

import com.li.pojo.User;

public interface UserService {

    User login(String userName, String password);

    int register(User user);
}
