package com.gec.hrml.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 陈其营
 */
public class JdbcUtils {

    private static DataSource dataSource;
    //ThreadLocal此类最大特点：存储的对象依赖线程创建副本
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();


    static {

        //读取druid.properties属性配置文件，获取输入流对象
        //获取类路径下的durid.properties文件资源
        InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");

        Properties properties=new Properties();
        try {
            properties.load(resourceAsStream);
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource()
    {
        return dataSource;
    }

    //从连接池，获取Connection对象
    public static Connection getConnection() throws SQLException {

        //先通过threadLocal对象，获取Connection对象（从当前线程获取Connection对象，如果Connection对象为空，则证明此线程并没有创建Connection对象）
        Connection connection = connectionThreadLocal.get();

        if(connection==null)
        {
            //从连接池获取Connection对象
            connection=dataSource.getConnection();
            //将当前线程创建的Connection对象，存储到ThreadLocal（意味connection对象绑定当前线程）
            connectionThreadLocal.set(connection);
        }

        return connection;
    }

    //关闭Connection对象
    public static void closeConnection(){

        try {
            getConnection().close();
            //将connection对象从threadlocal对象移除（意味connection对象与线程解除绑定）
            connectionThreadLocal.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
