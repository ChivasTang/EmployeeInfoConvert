package com.EmployeeInfoConvert.fs.service;

import com.EmployeeInfoConvert.fs.dao.*;
import com.EmployeeInfoConvert.fs.domain.Department;
import com.EmployeeInfoConvert.fs.domain.Employee;
import com.EmployeeInfoConvert.fs.domain.Position;
import com.EmployeeInfoConvert.fs.dto.Basic;
import com.EmployeeInfoConvert.fs.dto.Contact;
import com.EmployeeInfoConvert.fs.dto.Dep;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
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
    private static final String FILEPATH_SUFFIX_IN ="-info.csv";
    private static final String FILEPATH_PREFIX_OUT="/resources/output/";
    private static final String FILEPATH_SUFFIX_OUT ="-info-converted.csv";
    private static final String ZIP_SUFFIX =".zip";

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
                logger.info(basic.toString());
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
            while ((line = br.readLine()) != null)
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
                logger.info(contact.toString());
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
                logger.info(dep.toString());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return depList;
    }

    @Override
    public void deletAllFiles(File file) {
        if (file == null) {
            return;
        }
        //文件目录存在？（包括文件及文件夹）
        if (file.exists()) {
            //是文件？
            if (file.isFile()) {
                logger.info(file.getAbsolutePath());
                file.delete();
            } else if (file.isDirectory()) {
                //接收文件夹目录下所有的文件实例
                File[] listFiles = file.listFiles();
                //文件夹为空 递归出口
                if (listFiles == null) {
                    return;
                }
                for (File file2 : listFiles) {
                    //foreach遍历删除文件 递归
                    deletAllFiles(file2);
                    logger.info("正在删除文件夹 " + file.getAbsolutePath());
                }
                //递归跳出来的时候删除空文件夹
//                logger.info("正在删除文件夹" + file.getAbsolutePath());
//                file.delete();
            }
        }
    }

    @Override
    public void writeCSVEmployee(String fileName) {
        List<Employee> employeeList = employeeDao.findAllEmployee();
        try {
            if (fileName.isEmpty()) {
                fileName = "employee";
            }
            File csv = new File(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT + fileName + FILEPATH_SUFFIX_OUT);
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
                str += employee.getHireday() + "\r\n";
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
            File csv =new File(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT + fileName + FILEPATH_SUFFIX_OUT);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
            bw.newLine();
            for (Department department:departmentList){
                String str="";
                str+=department.getId()+",";
                str+=department.getName()+"\r\n";
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
            File csv =new File(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT + fileName + FILEPATH_SUFFIX_OUT);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
            bw.newLine();
            for (Position position:positionList){
                String str="";
                str+=position.getId()+",";
                str+=position.getName()+"\r\n";
                bw.write(str);
            }
            bw.close();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void prepareZipWithPwd(String[] fileNames, String zipName, String password) throws ZipException {
        ZipFile zipFile=new ZipFile(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT +zipName+ ZIP_SUFFIX);
            ArrayList<File> filesToAdd=new ArrayList<>();
            for(String fileName:fileNames){
                filesToAdd.add(new File(FILEPATH_PREFIX+FILEPATH_PREFIX_OUT+fileName+ FILEPATH_SUFFIX_OUT));
            }
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parameters.setPassword(password);
            zipFile.addFiles(filesToAdd, parameters);
    }

    private static BufferedReader getBr(String fileName) {
        String filePath = FILEPATH_PREFIX + FILEPATH_PREFIX_IN + fileName + FILEPATH_SUFFIX_IN;
        FileInputStream fileInputStream;
        InputStreamReader streamReader;
        BufferedReader br=null;
        try {
            fileInputStream = new FileInputStream(filePath);
            streamReader=new InputStreamReader(fileInputStream,"UTF-8");
            br=new BufferedReader(streamReader);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return br;
    }
}
