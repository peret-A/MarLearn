package com.bdqn.jdbc;

import com.bdqn.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test03 {
/**
 * @author liuziyang
 * @data 2024-03-01-10:51
 */
public static void main(String[] args) {
    //创建Scanner对象
    Scanner scanner =new Scanner(System.in);
    //输入用户名 密码
    System.out.print("输入用户名: ");
    String root = scanner.next();
    System.out.print("输入密码: ");
    String pwd = scanner.next();
    Connection connection = null;
    PreparedStatement preparedStatement =null;
    ResultSet resultSet =null;
    try {
        //注册驱动，获取Connection对象
        connection = DriverManager.getConnection("jdbc:mysql:///db_practices02","root","123456");
        //定义sql语句
        String sql = "select * from user where username = ? and  password = ?";
        //创建PreparedStatement对象 发送sql
        preparedStatement = connection.prepareStatement(sql);
        //给占位符赋值
        preparedStatement.setString(1,root);
        preparedStatement.setString(2,pwd);
        //执行sql
        resultSet=preparedStatement.executeQuery();
        //处理结果集
        //创建User集合
        List<User> userList =new ArrayList<>();
        while (resultSet.next()){
            //创建USer对象
            User user =new User();
            user.setUid(resultSet.getInt("uid"));
            user.setName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPhone(resultSet.getString("phone"));
            userList.add(user);
        }
        //判空
        if(userList.isEmpty()){
            System.out.println("用户名或者密码错误");
        }else{
            System.out.println("登录成功");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }finally {
        //关闭资源
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
}
