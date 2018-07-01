package com.EmployeeInfoConvert.fs.service;


import com.EmployeeInfoConvert.fs.dao.*;
import com.EmployeeInfoConvert.fs.domain.Employee;
import com.EmployeeInfoConvert.fs.dto.Basic;
import com.EmployeeInfoConvert.fs.dto.Contact;
import com.EmployeeInfoConvert.fs.dto.Dep;
import org.apache.log4j.Logger;

import java.util.List;

public class EmployeeService implements IEmployeeService {
    private static Logger logger = Logger.getLogger(EmployeeService.class.getName());
    private ICSVService csvService=new CSVService();
    private IEmployeeDao employeeDao=new EmployeeDao();
    private IDepartmentDao departmentDao=new DepartmentDao();
    private IPositionDao positionDao=new PositionDao();

    @Override
    public void insertAll(String filePathBasicName, String filePathContactName, String filePathDepName) {
        List<Basic> basicList= csvService.readCSVBasic(filePathBasicName);
        List<Contact> contactList=csvService.readCSVContact(filePathContactName);
        List<Dep> depList=csvService.readCSVDep(filePathDepName);
        for(Basic basic:basicList){
            Employee employee=new Employee();
            employee.setName(basic.getName());
            employee.setKana(basic.getKana());
            employee.setSex(basic.getSex());
            employee.setBirthday(basic.getBirthday());

            for(Contact contact:contactList){
                if(employee.getName().equals(contact.getName())){
                    employee.setAddress(contact.getAddress1()+" "+contact.getAddress2());
                    employee.setTelephone(contact.getTelephone());
                    employee.setMobile(contact.getMobile());
                    employee.setEmail(contact.getEmail());
                }
            }

            for(Dep dep:depList){
                if(employee.getName().equals(dep.getName())){
                    employee.setHireday(dep.getHiredate());

//                    List<Department> departments=departmentDao.findAllDepartment();
//                    if(!departments.contains(department.getDepartmentName())){
//                        Department department=new Department();
//                        department.setId(departments.size());
//                        department.setName(department.getDepartmentName());
//                        departmentDao.saveDepartment(department);
//                    }

                    employee.setDepartmentId((departmentDao.findDepartmentByName(dep.getDepartmentName())).getId());

//                    List<Position> positions=positionDao.findAllPosition();
//                    if(!positions.contains(department.getPositionName())){
//                        Position position=new Position();
//                        position.setId(positions.size());
//                        position.setName(department.getPositionName());
//                        positionDao.savePosition(position);
//                    }

                    employee.setPositionId(positionDao.findPositionByName(dep.getPositionName()).getId());
                }
            }
            employeeDao.saveEmployee(employee);
            logger.info(employee.toString()+"has been saved.");
        }
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeDao.findAllEmployee();
    }
}
