package com.bdqn.jdbcDate;

import com.bdqn.entity.People;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SelectTest04 {
/**
 * @author liuziyang
 * @data 2024-03-01-15:28
 */
public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement preparedStatement =null;
    try {
        //注册驱动，获取数据库链接对象Connection对象
        connection = DriverManager.getConnection("jdbc:mysql:///db_practices02","root","123456");
        //定义sql
        String sql = "select * from people";
        //创建People集合
        List<People> peopleList =new ArrayList<>();
        //发送sql
        preparedStatement = connection.prepareStatement(sql);
        //给占位符赋值
        //执行sql
        ResultSet resultSet =preparedStatement.executeQuery();
        //对结果经行处理
        while (resultSet.next()){
            //创建People对象
            People people =new People();
            people.setPid(resultSet.getInt(1));
            people.setName(resultSet.getString(2));
            people.setAge(resultSet.getInt(3));
            people.setGender(resultSet.getString(4));
            people.setBirthday(resultSet.getDate(5));
            people.setIdentitycard(resultSet.getString(6));
            people.setPhone(resultSet.getString(7));
            people.setAddress(resultSet.getString(8));
            peopleList.add(people);
        }
        //遍历
        for(People people:peopleList){
            System.out.println(people);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }finally {
        //关闭资源
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
