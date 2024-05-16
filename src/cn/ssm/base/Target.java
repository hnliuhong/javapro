package cn.ssm.base;

import java.lang.reflect.Method;
import java.util.Arrays;

// 代理模式: 代理类，被代理类(目标对象),这两个类要实现相同的接口
public class Target implements IProxy {


    @Override
    public void save() {
//
        System.out.println("实现订单入库(订单信息).....");
        System.out.println("实现订单项1入库(购买的商品).....");
        System.out.println("实现订单项2入库(购买的商品).....");
        System.out.println("实现订单项3入库(购买的商品).....");
//
    }

    @Override
    public void query() {
        // 由于查询不涉及到数据的更新,因此不需要进行权限验证
        System.out.println("根据条件查询结果");
    }

    public static void main(String[] args) {
        Class clazz = Target.class.getInterfaces()[0];
        System.out.println(clazz);
        for(Method method:clazz.getDeclaredMethods()){
            System.out.println(method);
        }
    }
}
