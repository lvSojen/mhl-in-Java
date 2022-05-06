package com.hspedu.mhl.dao;

import com.hspedu.mhl.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

/**
 *
 * 开发BasicDAO, 是其他DAO的父类
 */
public class BasicDAO<T> { //泛型指定具体的类型

    private QueryRunner qr = new QueryRunner();

    // 开发通用的dml方法,针对任意的表
    public int update(String sql, Object... parameters) {
        Connection connection = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            int update = qr.update(connection,sql,parameters);
            return update;
        } catch (Exception e) {
            // 编译异常转运行异常,抛出
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    /**
     *
     * @param sql sql 语句,可以有问号
     * @param clazz 传入一个类的Class对象 比如Actor.class
     * @param parameters 传入 ? 的具体值,可以是多个
     * @return 根据News.class 返回对应的 ArrayList 集合
     */
    // 返回多个对象(即查询的结果是多行),针对任何表
    public List<T> queryMultiple(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<>(clazz),parameters);
        } catch (Exception e) {
            // 编译异常转运行异常,抛出
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    // 查询单行结果的通用方法
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<>(clazz),parameters);
        } catch (Exception e) {
            // 编译异常转运行异常,抛出
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    // 查询单行单列的方法, 即返回单值的方法
    public Object queryScaler(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler(),parameters);
        } catch (Exception e) {
            // 编译异常转运行异常,抛出
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }

    }



}
