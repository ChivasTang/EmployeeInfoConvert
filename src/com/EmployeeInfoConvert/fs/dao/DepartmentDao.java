package com.EmployeeInfoConvert.fs.dao;

import com.EmployeeInfoConvert.fs.db.DBAccess;
import com.EmployeeInfoConvert.fs.domain.Department;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements IDepartmentDao {
    private static Logger logger = Logger.getLogger(DepartmentDao.class.getName());

    @Override
    public List<Department> findAllDepartment() {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        List<Department> departmentList=new ArrayList<>();
        try {
            Department department=new Department();
            sqlSession=dbAccess.getSqlSession();
            departmentList=sqlSession.selectList("Department.findAllDepartment",department);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return departmentList;
    }

    @Override
    public Department findDepartmentById(Integer id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        Department department=new Department();
        try {
            sqlSession=dbAccess.getSqlSession();
            department=sqlSession.selectOne("Department.findDepartmentById", id);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return department;
    }

    @Override
    public Department findDepartmentByName(String name) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        Department department=new Department();
        try {
            sqlSession=dbAccess.getSqlSession();
            department=sqlSession.selectOne("Department.findDepartmentByName", name);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info(department.toString());
        return department;
    }

    @Override
    public void saveDepartment(Department department) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        try {
            sqlSession=dbAccess.getSqlSession();
            sqlSession.insert("Department.saveDepartment", department);
            sqlSession.commit();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
