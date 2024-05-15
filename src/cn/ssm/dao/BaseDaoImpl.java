package cn.ssm.dao;

import cn.ssm.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoImpl<T> {

    // 定义一个抽象方法,此方法传入ResultSet,子类根据自己的情况获取数据
    protected abstract T getRowMapper(ResultSet rs) throws SQLException;

    // 插入、更新、删除 都统称为更新, 数组: 限大小,限类型
    protected int update(String sql,Object[] param){
        // 获取conn连接对象
        Connection connection = null;
        PreparedStatement statement = null;
        // 预编辑SQL语句
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.prepareStatement(sql);
            // 设置参数
            for(int i=0;i<param.length;i++){
                statement.setObject(i+1,param[i]);
            }

//            statement.setDouble(2,product.getPrice());
//            statement.setString(3,product.getRemark());
            // 执行sql语句,并且返回受影响的行数
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally { // 正常、异常、return之后都会执行
            JdbcUtils.close(connection,statement);

        }
    }

    protected List<T> query(String sql,Object[] param){
        List<T> tList = new ArrayList<T>();
        // 获取conn连接对象
        Connection connection = null;
        PreparedStatement statement = null;
        // 结果集 (对应数据表)
        ResultSet rs = null;
        // 预编辑SQL语句
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.prepareStatement(sql);
            // 设置参数
            for(int i=0;i<param.length;i++){
                statement.setObject(i+1,param[i]);
            }
            rs = statement.executeQuery();
            while(rs.next()){ // 当前行是记录行则返回是true
                System.out.println("this:" + this);
                T t = this.getRowMapper(rs);  // 此方法由子类实现
                tList.add(t);
            }
            return tList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.close(connection,statement,rs);
        }
    }


}
