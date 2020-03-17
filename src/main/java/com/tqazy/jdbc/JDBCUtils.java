package com.tqazy.jdbc;

import com.tqazy.utils.PropertiesUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 散场前的温柔
 */
public class JDBCUtils {

    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    private static Map<String, String> map;

    /**
     * 获取数据库连接信息
     */
    private static void getProperties(String path) {
        List<String> list = new ArrayList<String>();
        list.add("driver");
        list.add("url");
        list.add("username");
        list.add("password");
        map = PropertiesUtils.readProperties(path, list);
    }

    /**
     * 获取数据库连接
     */
    public static void getConnection(String path) {
        try {
            if (map == null) {
                getProperties(path);
            }
            Class.forName(map.get("driver"));
            con = DriverManager.getConnection(map.get("url"), map.get("username"), map.get("password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据库方法
     * @param path
     * @param sql
     * @param args
     * @return
     */
    public static int update(String path, String sql, Object... args) {
        int num = 0;
        getConnection(path);
        // 如果创建连接失败，返回0行
        if (con == null) {
            System.out.println("创建数据库连接失败");
            return 0;
        }
        try {
            // 1. 通过connection的prepareStatement(sql)方法获取PreparedStatement实例
            ps = con.prepareStatement(sql);

            // 2. 调用PreparedStatement的setXxx(int index, Object val)设置占位符的值，index从1开始
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 3. 执行SQL语句:executeQuery()和executeUpdate()方法，注意：执行时不需要再传入SQL语句
            num = ps.executeUpdate();
        } catch (SQLException e) {
            // 从连接中获取Statement异常
            e.printStackTrace();
        } finally {
            // 关闭statement和connection连接
            close();
        }
        return num;
    }

    /**
     * 关闭数据库相关连接，释放数据库资源
     */
    public static void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
