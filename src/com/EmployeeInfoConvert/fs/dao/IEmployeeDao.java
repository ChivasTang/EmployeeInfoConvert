package com.EmployeeInfoConvert.fs.dao;

import com.EmployeeInfoConvert.fs.domain.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> findAllEmployee();
    Employee findEmployeeById(Integer id);
    Employee findEmployeeByName(String name);
    void saveEmployee(Employee employee);
}
