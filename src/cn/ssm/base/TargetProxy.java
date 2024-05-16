package cn.ssm.base;

import sun.nio.ch.SocketOpts;

// 目标对象的代理类, 目标对象和代理类都要实现相同的接口
// 静态代理缺点: 会生成很多代理类,不方便管理和维护, 可以采用Spring AOP来实现动态代理
public class TargetProxy implements IProxy {

    // 代理类,必须要有目标对象(被代理对象)
    private IProxy target = null;

    public void setTarget(IProxy target) {
        this.target = target;
    }

    public TargetProxy(IProxy target){
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("用户权限验证");
        this.target.save();
        System.out.println("提交事务(如果上述操作抛出异常则操作回滚,否则提交)");
    }

    @Override
    public void query() {
        this.target.query();
    }

    public static void main(String[] args) {
        // 创建代理类时必须要有目标对象
        Target target = new Target();
        TargetProxy targetProxy = new TargetProxy(target);
        targetProxy.save();
        targetProxy.query();
    }
}
