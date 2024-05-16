package cn.ssm.base;

import cn.ssm.service.ProductServiceImpl;

// 工厂主要用于创建对象(根据类来创建对象)
public class BeanFactory {

    // 给定义*.class文件创建此类型的对象
    public static <T> T getBean(String className,Class<T> tclazz){
        try {
            Class clazz = Class.forName(className);
            return (T)clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
       ProductServiceImpl serviceImpl = BeanFactory.getBean("cn.ssm.service.ProductServiceImpl",
               ProductServiceImpl.class);
        System.out.println(serviceImpl);
    }
}
