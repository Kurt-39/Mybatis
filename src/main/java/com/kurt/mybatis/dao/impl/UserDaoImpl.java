package com.kurt.mybatis.dao.impl;

import com.kurt.mybatis.dao.UserDao;
import com.kurt.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDaoImpl implements UserDao {
    //注入SqlSessionFactory
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    private SqlSessionFactory sqlSessionFactory;
    @Override
    public User findUserById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = null;
        try {
           user = sqlSession.selectOne("test.findUserById", id);
            System.out.println(user);
        }finally {
            sqlSession.close();
        }
        return user;
    }

    @Override
    public void insertUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert("test.insertUser",user);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }
}
