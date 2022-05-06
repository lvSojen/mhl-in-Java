package com.hspedu.mhl.utils;

import java.sql.Connection;
import java.util.UUID;

public class Test {

    public static void main(String[] args) throws Exception {
//        //测试Utility 工具类
//        System.out.println("输入一个整数: ");
//        int i = Utility.readInt();
//        System.out.println("i= "+i);
//
//        // 测试一下我们的JDBCUtilByDruid
//        Connection connection = JDBCUtilsByDruid.getConnection();
//        System.out.println(connection);
        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID().toString());
        }

    }
}
