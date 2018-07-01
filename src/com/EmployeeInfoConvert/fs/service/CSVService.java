package com.EmployeeInfoConvert.fs.service;

import com.EmployeeInfoConvert.fs.dao.*;
import com.EmployeeInfoConvert.fs.domain.Department;
import com.EmployeeInfoConvert.fs.domain.Employee;
import com.EmployeeInfoConvert.fs.domain.Position;
import com.EmployeeInfoConvert.fs.dto.Basic;
import com.EmployeeInfoConvert.fs.dto.Contact;
import com.EmployeeInfoConvert.fs.dto.Dep;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVService implements ICSVService {
    private static Logger logger = Logger.getLogger(CSVService.class.getName());
    private IEmployeeDao employeeDao=new EmployeeDao();
    private IDepartmentDao departmentDao=new DepartmentDao();
    private IPositionDao positionDao=new PositionDao();
    private static final String FILEPATH_PREFIX="E:/IdeaProjects/EmployeeInfoConvert";
    private static final String FILEPATH_PREFIX_IN="/resources/input/employee-";
    private static final String FILEPATH_SURFIX_IN ="-info.csv";
    private static final String FILEPATH_PREFIX_OUT="/resources/output/";
    private static final String FILEPATH_SURFIX_OUT ="-info-converted.csv";
    @Override
    public List<Basic> readCSVBasic(String fileName) {
        BufferedReader br=getBr(fileName);
        String line;
        String everyLine;
        List<Basic> basicList=new ArrayList<>();
        try {
            while ((line = br.readLine()) != null)
            {
                everyLine = line;
                String[] str=everyLine.split(",");
                Basic basic=new Basic();
                basic.setName(str[0]);
                basic.setKana(str[1]);
                basic.setSex(str[2]);
                String[] str_date=str[3].split("/");

                Date birthday=new Date(Integer.parseInt(str_date[0])-1900,Integer.parseInt(str_date[1])-1,Integer.parseInt(str_date[2]),00,00,00);
                basic.setBirthday(birthday);
                basicList.add(basic);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return basicList;
    }

    @Override
    public List<Contact> readCSVContact(String fileName) {
        BufferedReader br=getBr(fileName);
        String line;
        String everyLine;
        List<Contact> contactList=new ArrayList<>();
        try {
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                String[] str=everyLine.split(",");
                Contact contact=new Contact();
                contact.setName(str[0]);
                contact.setAddress1(str[1]);
                contact.setAddress2(str[2]);
                contact.setTelephone(str[3]);
                contact.setMobile(str[4]);
                contact.setEmail(str[5]);

                contactList.add(contact);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return contactList;
    }

    @Override
    public List<Dep> readCSVDep(String fileName) {
        BufferedReader br=getBr(fileName);
        String line;
        String everyLine;
        List<Dep> depList=new ArrayList<>();
        try {
            while ((line = br.readLine()) != null)
            {
                everyLine = line;
                String[] str=everyLine.split(",");
                Dep dep=new Dep();
                dep.setName(str[0]);
                dep.setDepartmentName(str[1]);
                dep.setPositionName(str[2]);
                String[] str_date=str[3].split("/");
                Date hireday=new Date(Integer.parseInt(str_date[0])-1900,Integer.parseInt(str_date[1])-1,Integer.parseInt(str_date[2]),00,00,00);
                dep.setHiredate(hireday);
                depList.add(dep);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return depList;
    }

    @Override
    public void writeCSVEmployee(String fileName) {
        List<Employee> employeeList = employeeDao.findAllEmployee();
        try {
            if (fileName.isEmpty()) {
                fileName = "employee";
            }
            File csv = new File(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT + fileName + FILEPATH_SURFIX_OUT);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            bw.newLine();
            for (Employee employee : employeeList) {
                String str = "";
                str += employee.getId() + ",";
                str += employee.getDepartmentId() + ",";
                str += employee.getName() + ",";
                str += employee.getKana() + ",";
                str += employee.getSex() + ",";
                str += employee.getBirthday() + ",";
                str += employee.getAddress() + ",";
                str += employee.getTelephone() + ",";
                str += employee.getMobile() + ",";
                str += employee.getEmail() + ",";
                str += employee.getPositionId() + ",";
                str += employee.getHireday() + ",";
                bw.write(str);
            }
            bw.close();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void writeCSVDepartment(String fileName) {
        List<Department> departmentList=departmentDao.findAllDepartment();
        try{
            if(fileName.isEmpty()){
                fileName="department";
            }
            File csv =new File(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT + fileName + FILEPATH_SURFIX_OUT);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
            bw.newLine();
            for (Department department:departmentList){
                String str="";
                str+=department.getId()+",";
                str+=department.getName()+",";
                bw.write(str);
            }
            bw.close();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void writeCSVPosition(String fileName) {
        List<Position> positionList=positionDao.findAllPosition();
        try{
            if(fileName.isEmpty()){
                fileName="position-info-converted";
            }
            File csv =new File(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT + fileName + FILEPATH_SURFIX_OUT);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
            bw.newLine();
            for (Position position:positionList){
                String str="";
                str+=position.getId()+",";
                str+=position.getName()+",";
                bw.write(str);
            }
            bw.close();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    private static BufferedReader getBr(String fileName) {
        String filePath = FILEPATH_PREFIX + FILEPATH_PREFIX_IN + fileName + FILEPATH_SURFIX_IN;
        File file = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        return br;
    }
}
