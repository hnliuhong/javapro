package cn.ssm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDaoImpl {
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
}
