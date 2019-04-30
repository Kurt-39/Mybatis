package com.kurt.mybatis.dao;

import com.kurt.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


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

}
