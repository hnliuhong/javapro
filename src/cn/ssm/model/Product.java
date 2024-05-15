package cn.ssm.model;

import java.util.Date;

// Proudct 类 对应 数据库的Product表
// Product对象 ==> Product表记录
// Product 属性 ==> Product 表的字段
public class Product extends Object {

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", remark='" + remark + '\'' +
                '}';
    }

    private Integer id;
    private String name;
    private Double price;
    private Date date;
    private String remark;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
