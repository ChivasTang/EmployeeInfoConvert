package com.EmployeeInfoConvert.fs.dao;

import com.EmployeeInfoConvert.fs.db.DBAccess;
import com.EmployeeInfoConvert.fs.domain.Employee;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IEmployeeDao {
    private static Logger logger = Logger.getLogger(EmployeeDao.class.getName());

    @Override
    public List<Employee> findAllEmployee() {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        List<Employee> employeeList=new ArrayList<>();
        try{
            Employee employee=new Employee();
            sqlSession=dbAccess.getSqlSession();
            employeeList=sqlSession.selectList("Employee.findAllEmployee",employee);
        }catch (IOException e) {
            logger.error(e.getMessage());
        }
        return employeeList;
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        Employee employee=new Employee();
        try {
            sqlSession=dbAccess.getSqlSession();
            employee=sqlSession.selectOne("Employee.findEmployeeById", id);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return employee;
    }

    @Override
    public Employee findEmployeeByName(String name) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        Employee employee=new Employee();
        try {
            sqlSession=dbAccess.getSqlSession();
            employee=sqlSession.selectOne("Employee.findEmployeeByName", name);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return employee;
    }

    @Override
    public void saveEmployee(Employee employee) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        try {
            sqlSession=dbAccess.getSqlSession();
            sqlSession.insert("Employee.saveEmployee", employee);
            sqlSession.commit();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
