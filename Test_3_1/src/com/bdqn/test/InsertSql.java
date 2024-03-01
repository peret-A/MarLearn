package com.bdqn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSql {
/**
 * @author liuziyang
 * @data 2024-03-01-14:47
 */
public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement preparedStatement =null;

    try {
        // 1、注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2 、获取数据库连接对象
        String url = "jdbc:mysql://127.0.0.1:3306/db_practices02";
        String user = "root";
        String password = "123456";
        connection = DriverManager.getConnection(url, user, password);

        // 3、编写SQL语句，SQL语句中需要的数据先使用占位符?表示，然后在执行前给占位符赋值
        String sql = "INSERT INTO `people`(`name`,`age`,`gender`,`birthday`,`identitycrd`,`phone`,`address`) VALUES(?,?,?,?,?,?,?);";

        // 4、获取发送SQL语句对象
        preparedStatement = connection.prepareStatement(sql);

        //创建People对象
        People people = new People("王五", 30, "男", DateUtils.strDateToUtilDate("1992-04-05"), "340825199204051926", "13845691236", "安徽合肥庐阳区");

        //5、绑定参数，给?赋值
        preparedStatement.setString(1, people.getName());
        preparedStatement.setInt(2,people.getAge());
        preparedStatement.setString(3,people.getGender());
        //setDate(Date date)方法中需要的参数date类型是java.sql.Date类型，People对象中的Date类型是java.util.Date，需要进行转换
        preparedStatement.setDate(4,DateUtils.utilDateToSqlDate(people.getBirthday()));
        preparedStatement.setString(5,people.getIdentitycard());
        preparedStatement.setString(6, people.getPhone());
        preparedStatement.setString(7, people.getAddress());

        // 6、执行SQL语句
        // DML语句：对于插入数据、修改数据、删除数据操作，都调用executeUpdate()方法，返回受影响的行数（int类型）
        // DQL语句：对于查询数据，调用executeQuery()方法，返回一个结果集（ResultSet类型）
        int result = preparedStatement.executeUpdate();

        // 7、处理结果，如果返回的受影响行数不为0，说明数据插入成功
        if (result != 0) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入失败");
        }

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // 8、释放资源，遵循“先开后关，后开先关”的原则
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
}
