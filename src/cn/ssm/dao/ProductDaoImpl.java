package cn.ssm.dao;
// 数据访问层,主要完成Product数据库操作

import cn.ssm.model.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 //  XML --> dtd
public class ProductDaoImpl
{


    private JdbcTemplate jdbcTemplate = null;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        System.out.println("ProductDaoImpl.setJdbcTemplate:" + jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProductDaoImpl(){
        System.out.println("ProductDaoImpl.ProductDaoImpl");
    }

    // 体现封装思想
    public int save(Product product){  // model类对数据进行封装
        String sql = "insert into product (name,price,remark) values (?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{product.getName(),product.getPrice(),product.getRemark()});
    }

    public int delete(int id){
        String sql = "delete from product where id = ?";
        return jdbcTemplate.update(sql,new Object[]{id});
    }

    public int update(Product product){
        String sql = "update product set name =?,price=?,remark=? where id=?";
        return jdbcTemplate.update(sql,new Object[]{product.getName(),product.getPrice(),
                product.getRemark(),product.getId()});
    }

    public List<Product> queryByName(String keyword){
        String sql = "select * from product where name like ?";
//        return super.query(sql,new Object[]{"%" + keyword + "%"},Product.class);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class),
                new Object[]{"%" + keyword + "%"});
    }

}
