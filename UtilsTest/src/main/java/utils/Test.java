package utils;

import java.util.Scanner;

public class Test {
/**
 * @author liuziyang
 * @data 2024-03-02-11:55
 */
public static void main(String[] args) {
    //创建数据库名 数据库表
    Scanner scanner =new Scanner(System.in);
    System.out.print("输入数据库名: ");
    String database = scanner.next();
    System.out.print("输入要实体化的表名: ");
    String table =scanner.next();
    MyStudentSqlUtils myStudentSqlUtils =new MyStudentSqlUtils(database,table);
    System.out.println(myStudentSqlUtils.getEntityClass());
}
}
