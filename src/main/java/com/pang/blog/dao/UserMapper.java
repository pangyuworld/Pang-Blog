package com.pang.blog.dao;

import com.pang.blog.entity.User;

import java.util.Map;

public interface UserMapper {
    User selectUser(String username);

    int insert(User record);

    int insertSelective(User record);

    String getSalt();

    String getHash();

    int updateUser(User userInfo);
}