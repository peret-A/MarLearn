package com.bdqn.jdbc;

import java.sql.*;
import java.util.Scanner;

public class test01 {
/**
 * @author liuziyang
 * @data 2024-03-01-9:36
 */
public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //创建Scanner对象
    Scanner scanner =new Scanner(System.in);
    //输入用户名 密码
    System.out.print("输入用户名: ");
    String root =scanner.next();
    System.out.print("输入密码: ");
    String pwd = scanner.next();
    Class.forName("com.mysql.jdbc.Driver");
    //注册驱动 获取connection对象
    Connection conn = DriverManager.getConnection("jdbc:mysql:///db_practices02","root","123456");
    //定义sql
    String sql ="select * from user where username = '"+root+"' and password = '"+pwd+"' ";
    //获取Statement对象
    Statement statement =conn.createStatement();
    //执行sql
    ResultSet resultSet = statement.executeQuery(sql);
    //对结果集处理
        if(resultSet.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("用户名或者密码错误");
    }
    //关闭资源
    resultSet.close();
    statement.close();
    conn.close();

}
}
