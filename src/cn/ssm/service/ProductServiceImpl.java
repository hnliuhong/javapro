package cn.ssm.service;
// 数据访问层,主要完成Product数据库操作

import cn.ssm.dao.ProductDaoImpl;
import cn.ssm.model.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//  XML --> dtd
public class ProductServiceImpl
{
    // 代码中没有依赖,所有的依赖都在xml中体现,运行时注入(IOC)
    private ProductDaoImpl productDao = null;

    // 注入私有的属性,需要set方法
    public void setProductDao(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }

    // 体现封装思想
   public int save(Product product){
        // 前端提交了要购买的商品,此处业务逻辑可能是商品去重, 总价金额优惠....
       System.out.println("插入功能.......");
       int count = productDao.save(product);
       Integer.parseInt("xxxx");
       return count;
   }

   public int delete(int id){
       return productDao.delete(id);
   }

   public int update(Product product){
       return productDao.update(product);
   }

   public List<Product> queryByName(String keyword,int currentPage){
       return productDao.queryByName(keyword,currentPage);
   }

}
