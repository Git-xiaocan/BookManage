package com.xiaocan.bookmanage.dao;

import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来完成对数据库通用操作的封装
 */
public abstract class BaseDAO {
    /**
     * 通过配置文件获得相应的取值
     **/
    private final static String DB_Driver = Configurations.get(SystemConstant.DB_DRIVER);
    private final static String DB_Url = Configurations.get(SystemConstant.DB_URL);
    private final static String DB_user = Configurations.get(SystemConstant.DB_USER);
    private final static String DB_pwd = Configurations.get(SystemConstant.DB_PWD);

    static {
        //静态块加载驱动
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得Connection 链接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_Url, DB_user, DB_pwd);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    /**
     * 关闭资源
     *
     * @param con
     * @param sta
     * @param res
     * @param presta
     */
    public static void db_Close(Connection con, Statement sta, ResultSet res, PreparedStatement presta) {
        try {
            if (res != null) res.close();

            if (presta != null) presta.close();

            if (sta != null) sta.close();

            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取操作数据库的statement对象
     *
     * @param sql
     * @param ob  可变
     * @return
     */
    public static PreparedStatement db_getStatement(String sql, Object... ob) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < ob.length; i++) {
            try {
                statement.setObject(i + 1, ob[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return statement;
    }


    /**
     * 返回数据库结果集对象
     *
     * @param sql
     * @param ob
     * @return
     */
    public static ResultSet db_Select(String sql, Object... ob) {
        PreparedStatement preparedStatement = db_getStatement(sql, ob);

        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    /**
     * 执行sql语句 返回影响的行数
     *
     * @param sql
     * @param ob
     * @return
     */
    public static int db_Update(String sql, Object... ob) {
        PreparedStatement preparedStatement = null;
        preparedStatement = db_getStatement(sql, ob);

        //影响的行数
        int count = -1;
        try {
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}

