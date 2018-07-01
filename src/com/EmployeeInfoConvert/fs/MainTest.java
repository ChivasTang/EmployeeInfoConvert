package com.EmployeeInfoConvert.fs;


import com.EmployeeInfoConvert.fs.domain.Employee;
import com.EmployeeInfoConvert.fs.service.EmployeeService;
import com.EmployeeInfoConvert.fs.service.IEmployeeService;
import org.apache.log4j.Logger;


public class MainTest {
    private static Logger logger = Logger.getLogger(MainTest.class.getName());
    public static void main(String[] args) {
        IEmployeeService employeeService=new EmployeeService();
        employeeService.insertAll("basic","contact","department");
        for(Employee employee:employeeService.findAllEmployee()){
            logger.info(employee.toString());
        }
    }
}
