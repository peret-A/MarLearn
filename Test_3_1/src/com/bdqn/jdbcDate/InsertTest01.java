package com.bdqn.jdbcDate;

import com.bdqn.entity.People;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsertTest01 {
/**
 * @author liuziyang
 * @data 2024-03-01-11:19
 */
public static void main(String[] args) {
    Connection conn =null;
    PreparedStatement preparedStatement =null;
    try {
        //注册驱动，获取Connection对象
        conn = DriverManager.getConnection("jdbc:mysql:///db_practices02","root","123456");
        //创建Peolpe对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        People people =new People(1003,"赵六",15,"男",simpleDateFormat.parse("2024-6-31"),"340825199204051929","1399999999","安徽合肥瑶海区");
        //定义sql
        String sql = "insert into people(name,age,gender,birthday,identitycrd,phone,address) values(?,?,?,?,?,?,?)";
        //发送sql 创建Statement对象
        preparedStatement= conn.prepareStatement(sql);
        //给占位符赋值
        preparedStatement.setString(1,people.getName());
        preparedStatement.setInt(2,people.getAge());
        preparedStatement.setString(3,people.getGender());
        preparedStatement.setDate(4,new java.sql.Date(people.getBirthday().getTime()));
        preparedStatement.setString(5,people.getIdentitycard());
        preparedStatement.setString(6,people.getPhone());
        preparedStatement.setString(7,people.getAddress());
        //执行sql
        int num = preparedStatement.executeUpdate();
        //处理结果
        System.out.println(num==0?"添加失败":"添加成功");
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
