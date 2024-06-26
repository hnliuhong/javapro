package cn.ssm.service;

import cn.ssm.model.Product;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class ProductServicemplTest {

    private static ClassPathXmlApplicationContext context = null;
    private static ProductServiceImpl productService = null;

    @org.junit.BeforeClass   // 在测试类运行之前执行且执行一次
    public static void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring-bean.xml");
        productService = context.getBean("productService",ProductServiceImpl.class);
    }

    @org.junit.AfterClass   // 在测试类运行完毕之后执行一次,主要用于销毁资源
    public static void tearDown() throws Exception {
        context.close();
    }

    @org.junit.Test
    public void save() {
        Product product = new Product();
        product.setName("优盘D");
        productService.save(product);
    }

    @org.junit.Test
    public void delete() {
        productService.delete(14);
    }

    @org.junit.Test
    public void update() {
        Product product = new Product();
        product.setId(9);
        product.setName("华为运动手表");
        product.setPrice(new BigDecimal(4999));
        productService.update(product);
    }

    @org.junit.Test
    public void queryByName() {
        List<Product> proList = productService.queryByName("",2);
        for(Product p:proList){
            System.out.println(p);
        }
    }
}