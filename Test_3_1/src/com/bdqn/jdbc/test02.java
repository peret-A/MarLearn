package com.bdqn.jdbc;

import com.bdqn.entity.User;
import com.mysql.jdbc.Driver;

import java.sql.*;

public class test02 {
/**
 * @author liuziyang
 * @data 2024-03-01-10:24
 */
public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement preparedStatement =null;
    ResultSet resultSet =null;

    try {
        //注册驱动，获取Connection对象
        connection= DriverManager.getConnection("jdbc:mysql:///db_practices02","root","123456");
        //定义sql
        String sql = "select * from user where uid = ?";
        //获取发送sql，PreparedStatement对象
        preparedStatement=connection.prepareStatement(sql);
        //给定义的占位符赋值
        preparedStatement.setInt(1,1001);
        //执行sql
        resultSet=preparedStatement.executeQuery();
        //处理结果
        while (resultSet.next()){
            int uid = resultSet.getInt(1);
            String username =resultSet.getString(2);
            String password =resultSet.getString(3);
            String phone =resultSet.getString(4);
            User user =new User(uid,username,password,phone);
            System.out.println(user);
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
