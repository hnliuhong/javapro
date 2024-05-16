package cn.ssm.dao;
// 数据访问层,主要完成Product数据库操作

import cn.ssm.model.Product;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;

//  XML --> dtd
public class ProductDaoImpl
{
    // 依赖mybatis
    private SqlSessionFactory sqlSessionFactory = null;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int save(Product product){  // model类对数据进行封装
        SqlSession sqlSession =  sqlSessionFactory.openSession();
        System.out.println(ProductDaoImpl.class.getName());
        return sqlSession.insert(ProductDaoImpl.class.getName() + ".save",product);
    }

    public int delete(int id){
        SqlSession sqlSession =  sqlSessionFactory.openSession();
        return sqlSession.delete(ProductDaoImpl.class.getName() + ".delete",id);
    }

    public int update(Product product){
        SqlSession sqlSession =  sqlSessionFactory.openSession();
        return sqlSession.update(ProductDaoImpl.class.getName() + ".update",product);
    }

    public List<Product> queryByName(String keyword,int currentPage){
        int size = 5;
        Map<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("keyword","%" + keyword + "%");
        hashMap.put("start",(currentPage - 1) * size);
        hashMap.put("size",size);
//        SELECT * FROM product where name like #{keyword} LIMIT #{start},#{size};
        SqlSession sqlSession =  sqlSessionFactory.openSession();
        return sqlSession.selectList(ProductDaoImpl.class.getName() + ".query",hashMap);
    }

}
