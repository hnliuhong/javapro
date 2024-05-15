package cn.ssm.model;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RefDemo {
    //  反射介绍
    public static void main(String[] args) throws Exception {
       // 为了增加灵活性,Java引入反射的概念 (Java一切皆为对象)
       Class clazz = Product.class;  // 类型
        Product product = (Product)clazz.newInstance(); // 根据类型创建对象,默认会调用构造方法等同于 new Product()
        System.out.println(product);
    }

}
