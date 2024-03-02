package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class MyStudentSqlUtils {
    /**
     * @author liuziyang
     * @data 2024-03-02-10:04
     */
//jdbc链接到本机数据库 localhost:3306 或者 127.0.0.1:3306 是可省略的
    private String url = "jdbc:mysql:///";
    //数据库用户名
    private String root = "root";
    //数据库密码
    private String password = "123456";
    //获取链接数据库
    private Connection connection;
    //数据库的表名
    private String table = "student";
    //发送sql语句对象
    private PreparedStatement preparedStatement;
    //字段名称
    private String[] names;
    //字段类型
    private String[] types;

    public MyStudentSqlUtils(String database, String table) {
        this.table = table;
        url += database;
        ResultSet resultSet = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库链接对象
            connection = DriverManager.getConnection(url, root, password);
            //定义sql语句
            String sql = "select * from " + table;
            //获取发送sql对象
            preparedStatement = connection.prepareStatement(sql);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            getClassContains(resultSet);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public MyStudentSqlUtils() {
        //用于测试 默认数据库名 db_practices02 数据库表 student
        ResultSet resultSet = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库链接对象
            connection = DriverManager.getConnection(url, root, password);
            //定义sql语句
            String sql = "select * from " + table;
            //获取发送sql对象
            preparedStatement = connection.prepareStatement(sql);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            getClassContains(resultSet);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void getClassContains(ResultSet resultSet) {
        try {
            //获取字段对象
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取列数
            int size = resultSetMetaData.getColumnCount();
            //获取字段名字
            names = new String[size + 1];
            types = new String[size + 1];
            for (int i = 1; i <= size; i++) {
                names[i] = resultSetMetaData.getColumnName(i);
                //获取的字段类型
                types[i] = SqlToString(resultSetMetaData.getColumnTypeName(i));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String SqlToString(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("INTEGER") || sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("double")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
            return "java.util.Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    public StringBuilder getEntityClass() {
        //生成对应表的实体类
        //获取类名 根据标识符规则 类名首字母大写
        String className = table.substring(0, 1).toUpperCase() + table.substring(1);
        //创建StringBuilder 即不定长字符串 存储内容
        StringBuilder stringBuilder = new StringBuilder("");
        //判断表是否存在
        if (names == null && types == null)
            return stringBuilder;
        //创建文件的包名
        stringBuilder.append("package com.bdqn.entity;\r\n\r\n");
        //拼接类名
        stringBuilder.append("public class " + className + "{\r\n");
        //拼接字段
        for (int i = 1; i < names.length; i++) {
            stringBuilder.append(getAttrbuteString(names[i], types[i]));
        }
        //拼接set和get方法
        for(int index=1; index < names.length ; index++){
            stringBuilder.append(getGetMethodString(names[index],types[index]));
            stringBuilder.append(getSetMethodString(names[index],types[index]));
        }
        stringBuilder.append("}\r\n");
        //输出到文件中
        String s1 = "com.bdqn.entity".replace(".", "\\");
        String s2 = "src"+ File.separator+"main"+File.separator+"java"+File.separator+s1;
        String s3 =   System.getProperty("user.dir")+File.separator+s2;
        File file = new File(s3);
        if(!file.exists()){
            file.mkdirs();
        }
        BufferedWriter write = null;
        try {
            write = new BufferedWriter(new FileWriter(s3+File.separator+className+".java"));
            write.write(stringBuilder.toString());
            write.close();
        } catch (IOException e) {

            e.printStackTrace();
            if (write != null)
                try {
                    write.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
        return stringBuilder;


    }

    //获取字段
    private StringBuffer getAttrbuteString(String name, String type) {
        if (!check(name, type)) {
            System.out.println("类中有属性或者类型为空");
            return null;
        }
        ;
        String format = String.format("private %s %s;\n\r", new String[]{type, name});
        return new StringBuffer(format);
    }

    //校验name和type是否合法
    private boolean check(String name, String type) {
        if ("".equals(name) || name == null || name.trim().length() == 0) {
            return false;
        }
        if ("".equals(type) || type == null || type.trim().length() == 0) {
            return false;
        }
        return true;
    }
    private StringBuffer getGetMethodString(String name, String type) {
        if(!check(name,type)) {
            System.out.println("类中有属性或者类型为空");
            return null;
        };
        String Methodname = "get"+GetTuoFeng(name);
        String format = String.format("public %s %s(){\n\r", new Object[]{type,Methodname});
        format += String.format("        return this.%s;\r\n", new Object[]{name});
        format += "    }\r\n";
        return new StringBuffer(format);
    }
    //将名称首字符大写
    private String GetTuoFeng(String name) {
        name = name.trim();
        if(name.length() > 1){
            name = name.substring(0, 1).toUpperCase()+name.substring(1);
        }else
        {
            name = name.toUpperCase();
        }
        return name;
    }
    /*
     * 获取字段的get方法字符串*/
    private Object getSetMethodString(String name, String type) {
        if(!check(name,type)) {
            System.out.println("类中有属性或者类型为空");
            return null;
        };
        String Methodname = "set"+GetTuoFeng(name);
        String format = String.format("public void %s(%s %s){\n\r", new Object[]{Methodname,type,name});
        format += String.format("        this.%s = %s;\r\n", new Object[]{name,name});
        format += "    }\r\n";
        return new StringBuffer(format);
    }

}