package com.EmployeeInfoConvert.fs.web.servlet;

import com.EmployeeInfoConvert.fs.service.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class CSVServlet extends HttpServlet {
    private static final long serialVersionUID = 5297640512706840372L;
    private static Logger logger = Logger.getLogger(CSVServlet.class.getName());
    private ICSVService csvService=new CSVService();
    private IEmployeeService employeeService=new EmployeeService();
    private ISQLService sqlService=new SQLService();
    private static final String pathName= "E:\\IdeaProjects\\EmployeeInfoConvert\\src\\db.sql";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("データベースを初期化中です。少々お待ち下さい。。。");
        sqlService.initializeDB(pathName);
        logger.info("フォルダーを初期化中です。少々お待ち下さい。。。");
        csvService.deletAllFiles(new File("E:\\IdeaProjects\\EmployeeInfoConvert\\resources\\output\\"));
        logger.info("ファイルを読み込んでいる。少々お待ち下さい。。。");
        employeeService.insertAll("basic","contact","department");
        logger.info("社員情報を読み込みました、整理しています。。。");
        csvService.writeCSVEmployee("employee");
        logger.info("社員情報CSVファイルを作成しました。。。");
        csvService.writeCSVDepartment("department");
        logger.info("部門情報CSVファイルを作成しました。。。");
        csvService.writeCSVPosition("position");
        logger.info("職位情報CSVファイルを作成しました。。。");
        req.getRequestDispatcher("email.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        logger.info("CSVServlet has been destroyed.....");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("CSVServlet has been initialized.....");
    }
}
