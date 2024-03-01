package com.bdqn.jdbcDate;

import com.bdqn.entity.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DeleteTest03 {
/**
 * @author liuziyang
 * @data 2024-03-01-15:21
 */
public static void main(String[] args) {
    Connection connection =null;
    PreparedStatement preparedStatement =null;
    try {
        //注册驱动，获取数据库链接对象Connection
        connection= DriverManager.getConnection("jdbc:mysql:///db_practices02","root","123456");
        //创建People对象
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        People people =new People(1003,"赵六",15,"男",simpleDateFormat.parse("2024-6-31"),"340825199204051929","1399999999","安徽合肥瑶海区");
        //定义sql
        String sql = "delete from people where pid = ?";
        //获取发送sql对象preparedstatement
        preparedStatement = connection.prepareStatement(sql);
        //给占位符赋值
        preparedStatement.setInt(1,people.getPid());
        //执行sql
        int num = preparedStatement.executeUpdate();
        //对结果经行处理
        System.out.println(num==0?"删除失败":"删除成功");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ParseException e) {
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
