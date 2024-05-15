package cn.ssm.dao;

import cn.ssm.model.Product;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDaoImpl<T> {

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
            // 执行sql语句,并且返回受影响的行数
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally { // 正常、异常、return之后都会执行
            JdbcUtils.close(connection,statement);
        }
    }

    protected List<T> query(String sql,Object[] param, Class<T> clazz){
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
            // 获取结果集的元数据信息
            ResultSetMetaData metaData =  rs.getMetaData();


            while(rs.next()){ // 当前行是记录行则返回是true
                T obj = clazz.newInstance();
                // 根据列名来获取当前记录的值
                for(int i=1;i<=metaData.getColumnCount();i++){
                    String colName = metaData.getColumnName(i);
//                    rs.getString("name")
                      Object colValue = rs.getObject(colName);
                      // 要根据列名,去获取clazz里面的属性名
                      Field filed = clazz.getDeclaredField(colName);
                      filed.setAccessible(true);   // 取消安全性检查,私有属性可以赋值
                      filed.set(obj,colValue);
                }
                tList.add(obj);
            }
            return tList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.close(connection,statement,rs);
        }
    }

}
