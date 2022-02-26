package com.gec.hrml.utils;


import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 1
 */
public abstract class BaseDao<T> {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 定义一个能够实现添加、删除、修改的公共方法
     * @param sql
     * @param params 参数
     * @return
     */
    public int update(String sql, Object... params) {

        try {
            Connection conn = JdbcUtils.getConnection();
            return queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            JdbcUtils.closeConnection();
        }
    }

    //定义一个查询的公共方法

    /**
     * 执行查询一行数据的sql语句，将结果集封装到JavaBean对象中
     *
     * @param clazz
     * @param sql
     * @param params
     * @return
     */
    public T getBean(Class<T> clazz, String sql, Object... params) {

        //开启下划线->驼峰转换所用
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);

        try {
            Connection conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new BeanHandler<>(clazz, processor), params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            JdbcUtils.closeConnection();
        }
    }


    /**
     * 获取多个对象的公共查询方法
     * @param clazz
     * @param sql
     * @param params
     * @return
     */
    public List<T> getBeanList(Class<T> clazz, String sql, Object... params) {

        //开启下划线->驼峰转换所用
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);

        try {
            Connection conn=JdbcUtils.getConnection();
            System.out.println(sql+"111111111的"+params.toString());
            return queryRunner.query(conn,sql,new BeanListHandler<>(clazz,processor),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            JdbcUtils.closeConnection();
        }
    }

    /**
     * 得到总条数
     * @param sql
     * @param params
     * @return
     */
    public Long getCountValue(String sql, Object... params){

        try {
            Connection conn=JdbcUtils.getConnection();
            return queryRunner.query(conn,sql,new ScalarHandler<>(),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            JdbcUtils.closeConnection();
        }
    }


}


