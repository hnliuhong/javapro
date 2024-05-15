package cn.ssm.model;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RefDemo {
    //  反射介绍
    public static void main(String[] args) throws Exception {
       Product product = new Product();
       product.setName("admin");
       System.out.println(product.getName());
       // 为了增加灵活性,Java引入反射的概念 (Java一切皆为对象)
       Class clazz = Product.class;
       // 通过类全名获取 Product.class文件 (对象)
       Class clazz2 = Class.forName("cn.ssm.model.Product");
       Class clazz3 = product.getClass();
        System.out.println(clazz == clazz2 && clazz2 == clazz3);
//        有一个类型是Class类型(与String没什么不同),"abc" 就是一个String类型的对象,
//        因此 *.class文件就是Class类型的对象
          // File类型，则abc.txt 就是File类型的对象
        // 获取 *.class文件中的属性和方法 getMethods 获取当前类和父类的公共的方法
        for(Method m:clazz.getDeclaredMethods()){  // getDeclaredMethods 当前类所有方法包括私有和静态方法,但是不包括继承的方法
            System.out.println(m);
        }

        Method method = clazz.getMethod("setName", String.class);
        method.invoke(product,"admin2");  // product.setName("admin");
        System.out.println(product.getName());

        for(Field f:clazz.getDeclaredFields()){   // 前类所有属性包括私有、静态属性
            System.out.println(f);
        }

        // 直接通过属性赋值
        Field field = clazz.getDeclaredField("remark");
        field.setAccessible(true);
        field.set(product,"采用反射动态赋值产品详细介绍");
        System.out.println(product.toString());
    }

}
