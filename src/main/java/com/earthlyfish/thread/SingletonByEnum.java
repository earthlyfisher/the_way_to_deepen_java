package com.earthlyfish.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 使用枚举实现单例
 * Created by earthlyfisher on 2017/3/13.
 */
public class SingletonByEnum {

    private SingletonByEnum() {
    }

    private enum MySingletonEnum {

        connectionFactory;

        private Connection connection;

        MySingletonEnum() {
            System.out.println("创建单例连接");
            String url = "";
            String password = "";
            String userName = "";
            String driveName = "";
            try {
                Class.forName(driveName);
                connection = DriverManager.getConnection(url, userName, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Connection getConnection() {
            return connection;
        }
    }

    public static Connection getConnection() {
        return MySingletonEnum.connectionFactory.getConnection();
    }
}
