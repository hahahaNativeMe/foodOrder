package com.lsu.bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class BaseDao {
    static DataSource dataSource;
    // 静态代码块(静态初始化)返回数据源对象
    static {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sampleDS");
        }catch(NamingException ne){
            System.out.println("Exception:"+ne);
        }
    }
    // 返回一个连接对象
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    //关闭连接
    public static void close(Connection connection,Statement statement,ResultSet resultSet){
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

