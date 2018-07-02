package com.EmployeeInfoConvert.fs.web.servlet;

import com.EmployeeInfoConvert.fs.domain.Employee;
import com.EmployeeInfoConvert.fs.service.CSVService;
import com.EmployeeInfoConvert.fs.service.EmployeeService;
import com.EmployeeInfoConvert.fs.service.ICSVService;
import com.EmployeeInfoConvert.fs.service.IEmployeeService;
import com.EmployeeInfoConvert.fs.util.RandomStringUtils;
import net.lingala.zip4j.exception.ZipException;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailServlet extends HttpServlet {
    private static final long serialVersionUID = -6054123152537176443L;
    private static final Logger logger = Logger.getLogger(EmailServlet.class.getName());

    private static final String FILEPATH_PREFIX = "E:/IdeaProjects/EmployeeInfoConvert";
    private static final String FILEPATH_PREFIX_OUT = "/resources/output/";
    private static final String ZIP_SUFFIX = ".zip";
    private static final String myEmailAccount = "xxxxxxxx@163.com";
    private static final String myEmailPassword = "xxxxxxxx";
    private static final String myEmailSMTPHost = "smtp.163.com";

    private ICSVService csvService = new CSVService();
    private IEmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] fileNames = {"position", "department", "employee"};
        String zipName;
        String password;
        List<Employee> employeeList = employeeService.findAllEmployee();

        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            if (!req.getParameter("zipFimeName").isEmpty()) {
                zipName = req.getParameter("zipFimeName");
            } else {
                zipName = "EmployeeInfoConverted";
            }
            password = RandomStringUtils.getPassword();
            logger.info("zip文件的密码为：" + password);
            csvService.prepareZipWithPwd(fileNames, zipName, password);


            message.setFrom(new InternetAddress(myEmailAccount, "Chivas Tang", "UTF-8"));
            for (Employee employee : employeeList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getEmail(), employee.getEmail(), "UTF-8"));
            }
            message.setSubject("社員情報処理結果通知", "UTF-8");

            MimeBodyPart text = new MimeBodyPart();
            text.setContent("<h1>密码为：-------<" + password + ">-------</h1>", "text/html;charset=UTF-8");

            MimeBodyPart attachment = new MimeBodyPart();
            DataHandler dh2 = new DataHandler(new FileDataSource(FILEPATH_PREFIX + FILEPATH_PREFIX_OUT + zipName + ZIP_SUFFIX));
            attachment.setDataHandler(dh2);
            attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(attachment);
            mm.setSubType("mixed");

            message.setContent(mm);
            message.setSentDate(new Date());
            message.saveChanges();

            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (ZipException | MessagingException e) {
            logger.error(e.getMessage());
        }


    }

    @Override
    public void destroy() {
        super.destroy();
        logger.info("EmailServlet has been initialized.....");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("EmailServlet has been initialized.....");
    }
}
