package cn.ssm.base;

// 此类关注的是横切面的代码,代码放在方法中,方法是放在类中,因此此类称为切面类
public class AOPClass {
    // 实现权限验证
    public void before(){
        System.out.println("验证权限是否合法........");
    }
    public void after_e(){
        System.out.println("只有出现异常,此方法才会被切入!");
    }
}
