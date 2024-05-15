package cn.ssm.dao;
// 数据访问层,主要完成Product数据库操作

import cn.ssm.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println(100);
            // 执行sql语句,并且返回受影响的行数
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally { // 正常、异常、return之后都会执行
            System.out.println("ProductDaoImpl.save");
            JdbcUtils.close(connection,statement);

        }
    }

    public List<Product> queryByName(String keyword){
        List<Product> proList = new ArrayList<Product>();
        String sql = "select * from product where name like ?";
        // 获取conn连接对象
        Connection connection = null;
        PreparedStatement statement = null;
        // 结果集 (对应数据表)
        ResultSet rs = null;
        // 预编辑SQL语句
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,"%" + keyword + "%");
            rs = statement.executeQuery();
            while(rs.next()){ // 当前行是记录行则返回是true
                // product表中的记录采用product对象来存储
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setRemark(rs.getString("remark"));
                product.setDate(rs.getDate("date"));
                // 当前存储了记录的product对象,存储到List中
                proList.add(product);
            }
            return proList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.close(connection,statement,rs);
        }
    }

    public static void main(String[] args) {
        ProductDaoImpl productDao = new ProductDaoImpl();
        // 数据应该从 前端 --> 业务逻辑 --> 数据访问
//        Product product = new Product();
//        product.setName("GPU笔记本2");
//        product.setPrice(8888.99);
//        product.setRemark("AI智能化,支持GPU");
//        int res = productDao.save(product);
//        System.out.println(res);
        // 查询测试
        List<Product> proList = productDao.queryByName("");
        for(Product p:proList){
            System.out.println(p);
        }
    }
}
