package com.kurt.mybatis;

import com.kurt.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MybatisFirst {
    //会话工厂
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void createSqlSessionFactory() throws IOException {
        //配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //根据id查询
    @Test
    public void testFindUserByID() {
        //数据库实例
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            User user = sqlSession.selectOne("test.findUserById", 10);
            System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }
    @Test
    public void testFindUserByUsername(){
        SqlSession sqlSession = getSqlSession();
        List<Object> list = sqlSession.selectList("test.findUserByUsername", "四");
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void testInsertUser() throws Exception{
        SqlSession sqlSession = getSqlSession();
        User user = new User();
        user.setUsername("BOB");
        user.setAddress("TIANMEN");
        String birthday = "1993-7-4";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        user.setBirthday(date);
        user.setSex("1");
        int insert = sqlSession.insert("test.insertUser", user);
        System.out.println(user.getId());
        sqlSession.commit();
        sqlSession.close();
    }
    //delete
    @Test
    public void deleteUser(){
        SqlSession sqlSession = getSqlSession();
        sqlSession.delete("test.deleteUser",40);
        sqlSession.commit();
        sqlSession.close();
    }
    //update
    @Test
    public void updateUser() throws Exception{
        SqlSession sqlSession = getSqlSession();
        User user = new User();
        user.setUsername("JIM");
        user.setAddress("TIANMEN");
        String birthday = "1993-7-4";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        user.setBirthday(date);
        user.setSex("1");
        user.setId(1);
        sqlSession.update("test.updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }
    //创建sqlSession
    private SqlSession getSqlSession() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSession;
    }

}
