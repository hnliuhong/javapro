package cn.ssm.dao;
// 数据访问层,主要完成Product数据库操作

import cn.ssm.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDaoImpl
{   // 体现封装思想
    public int save(Product product){  // model类对数据进行封装
        String sql = "insert into product (name,price,remark) values (?,?,?)";
        // 获取conn连接对象
        Connection connection = null;
        PreparedStatement statement = null;
        // 预编辑SQL语句
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.prepareStatement(sql);
            // 设置参数
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            statement.setString(3,product.getRemark());
            // 执行sql语句,并且返回受影响的行数
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("ProductDaoImpl.save");
            JdbcUtils.close(connection,statement);
        }
    }

    public static void main(String[] args) {
        ProductDaoImpl productDao = new ProductDaoImpl();
        // 数据应该从 前端 --> 业务逻辑 --> 数据访问
        Product product = new Product();
        product.setName("GPU笔记本2");
        product.setPrice(8888.99);
        product.setRemark("AI智能化,支持GPU");
        int res = productDao.save(product);
        System.out.println(res);
    }
}
