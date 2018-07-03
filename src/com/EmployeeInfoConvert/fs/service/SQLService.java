package com.EmployeeInfoConvert.fs.service;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLService implements ISQLService {
    private static Logger logger = Logger.getLogger(SQLService.class.getName());
    @Override
    public void initializeDB(String pathName) {
        Connection connection=getConnection();
        File file=new File(pathName);
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
            Reader reader=new BufferedReader(inputStreamReader);
            ScriptRunner runner = new ScriptRunner(connection);
            runner.runScript(reader);
            connection.close();
        }catch (SQLException | FileNotFoundException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/emp?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","root");
        } catch ( ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }
}
