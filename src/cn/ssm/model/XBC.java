package cn.ssm.model;

public class XBC {
    public static void main(String[] args) {
        try{
            Integer.parseInt("3.14");
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }finally {
            System.out.println("永远执行......");
        }
    }
}
