package com.EmployeeInfoConvert.fs.util;

import java.util.Arrays;
import java.util.Random;

public class RandomStringUtils {
//    private static Logger logger = Logger.getLogger(RandomStringUtils.class.getName());
    private static final String COD="12356789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ12356789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
    public static String getPassword(){
        char str[]=COD.toCharArray();
        int pwd[]=new int[8];
        for(int i=0;i<pwd.length;i++){
            Random random=new Random();
            int num=random.nextInt(100);
            pwd[i]=str[num];
        }
        return Arrays.toString(pwd);
    }
}
