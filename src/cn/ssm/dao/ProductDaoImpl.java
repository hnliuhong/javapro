package cn.ssm.dao;
// 数据访问层,主要完成Product数据库操作

import cn.ssm.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl<Product>
{   // 体现封装思想
    public int save(Product product){  // model类对数据进行封装
        String sql = "insert into product (name,price,remark) values (?,?,?)";
//        Object[] param
        return super.update(sql,new Object[]{product.getName(),product.getPrice(),product.getRemark()});
    }

    public int delete(int id){
        String sql = "delete from product where id = ?";
        return super.update(sql,new Object[]{id});
    }

    public int update(Product product){
        String sql = "update product set name =?,price=?,remark=? where id=?";
        return super.update(sql,new Object[]{product.getName(),product.getPrice(),
                product.getRemark(),product.getId()});
    }

    public List<Product> queryByName(String keyword){
        String sql = "select * from product where name like ?";
        return super.query(sql,new Object[]{"%" + keyword + "%"});
    }

    public static void main(String[] args) {
        ProductDaoImpl productDao = new ProductDaoImpl();
//        productDao.delete(2);

        // 数据应该从 前端 --> 业务逻辑 --> 数据访问
//        Product product = new Product();
//        product.setId(3);
//        product.setName("Iphone6");
//        product.setPrice(7888.99);
//        product.setRemark("AI手机智能化,支持GPU");
//        int res = productDao.update(product);
        // 查询测试
        List<Product> proList = productDao.queryByName("");
        for(Product p:proList){
            System.out.println(p);
        }
    }

    @Override
    protected Product getRowMapper(ResultSet rs) throws SQLException {
        // product表中的记录采用product对象来存储
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setRemark(rs.getString("remark"));
        product.setDate(rs.getDate("date"));
        return product;
    }
}
