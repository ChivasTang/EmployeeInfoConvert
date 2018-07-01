package com.EmployeeInfoConvert.fs.service;

import com.EmployeeInfoConvert.fs.domain.Employee;

import java.util.List;

public interface IEmployeeService {
    void insertAll(String filePathBasicName,String filePathContactName,String filePathDepName);
    List<Employee> findAllEmployee();
}
