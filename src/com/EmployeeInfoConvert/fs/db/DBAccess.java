package com.EmployeeInfoConvert.fs.db;

import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBAccess {
    public SqlSession getSqlSession() throws IOException {
        Reader reader= Resources.getResourceAsReader("com/EmployeeInfoConvert/fs/config/Mybatis-Configuration.xml");
        SqlSessionFactory sqlSessionFactory= (new SqlSessionFactoryBuilder()).build(reader);
        return sqlSessionFactory.openSession();
    }
}
