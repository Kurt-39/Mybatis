package com.kurt.mybatis.dao;

import com.kurt.mybatis.pojo.User;

public interface UserDao {
    public User findUserById(int id)throws Exception;
    public void insertUser(User user)throws Exception;
}
