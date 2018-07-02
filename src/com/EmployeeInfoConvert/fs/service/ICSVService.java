package com.EmployeeInfoConvert.fs.service;

import com.EmployeeInfoConvert.fs.dto.Basic;
import com.EmployeeInfoConvert.fs.dto.Contact;
import com.EmployeeInfoConvert.fs.dto.Dep;
import net.lingala.zip4j.exception.ZipException;

import java.util.List;

public interface ICSVService {
    List<Basic> readCSVBasic(String fileName);
    List<Contact> readCSVContact(String fileName);
    List<Dep> readCSVDep(String fileName);
    void writeCSVEmployee(String fileName);
    void writeCSVDepartment(String fileName);
    void writeCSVPosition(String fileName);
    void prepareZipWithPwd(String[] fileNames, String zipName, String password) throws ZipException;
}
