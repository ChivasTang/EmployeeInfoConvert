package com.EmployeeInfoConvert.fs.dao;

import com.EmployeeInfoConvert.fs.domain.Department;

import java.util.List;

public interface IDepartmentDao {
    List<Department> findAllDepartment();
    Department findDepartmentById(Integer id);
    Department findDepartmentByName(String name);
    void saveDepartment(Department department);
}
