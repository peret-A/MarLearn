package com.bdqn.jdbcDate;

import com.bdqn.entity.People;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UpdateTest02 {
/**
 * @author liuziyang
 * @data 2024-03-01-11:31
 */
public static void main(String[] args) {
    Connection conn =null;
    PreparedStatement preparedStatement =null;
    try {
        //注册驱动，获取Connection对象
        conn = DriverManager.getConnection("jdbc:mysql:///db_practices02","root","123456");
        //创建Peolpe对象
        String birthday = "1997-05-26";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        People people =new People(1003,"张三",32,"男",simpleDateFormat.parse(birthday),"340825199204051925","13888889999","安徽合肥瑶海区");
        //定义sql
        String sql = "update people set birthday = ? where pid = ? ";
        //发送sql 创建Statement对象
        preparedStatement = conn.prepareStatement(sql);
        //给占位符赋值
        preparedStatement.setDate(1,new java.sql.Date(people.getBirthday().getTime()));
        preparedStatement.setInt(2,people.getPid());
        //执行sql
        int num = preparedStatement.executeUpdate();
        //处理结果
        System.out.println(num==0?"修改失败":"修改成功");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    } finally {
        //关闭资源
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
}
