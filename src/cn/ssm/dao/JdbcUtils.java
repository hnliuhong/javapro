package cn.ssm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 连接数据操作 （驱动、账号、密码、连接字符串）
// 工具类:最好是类模式或者是单例模式
public class JdbcUtils {
    private static String url = "jdbc:mysql://localhost:3306/demo";
    private static String username = "root";
    private static String password = "root";
    // 构造方法,此方法默认调用,如果构造方法私有,则说明不支持对象创建,则采用类模式
    private JdbcUtils(){
        System.out.println("JdbcUtils.JdbcUtils");
    }

    // 数据库驱动: 类似操作说明书
    static {   // 静态块: 在类加载到虚拟机是会执行且执行一次 (驱动一般放到静态中)
        // 根据类全名加载驱动
        System.out.println("--------");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 连接数据库
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // 普通的属性,每个对象都有一份,而类属性整体只有一份
        // 普通方法是对象调用,不同的对象都可以调用普通方法,而类方法（static）只有类才能调用
        System.out.println(JdbcUtils.getConnection());
        System.out.println(JdbcUtils.getConnection());
    }


}
