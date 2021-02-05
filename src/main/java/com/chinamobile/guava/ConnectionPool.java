package com.chinamobile.guava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool implements IConnectionPool {

    private CopyOnWriteArrayList<Connection> freeConnection = new CopyOnWriteArrayList<Connection>();
    private CopyOnWriteArrayList<Connection> activeConnection = new CopyOnWriteArrayList<Connection>();

    private DbBean dbBean;

    private int countConnect = 0;

    public ConnectionPool(DbBean dbBean) {
        // 获取配置文件信息
        this.dbBean = dbBean;
        init();
    }

    private void init() {
        if (dbBean == null) {
            return;// 注意最好抛出异常
        }
        // 1.获取初始化连接数
        for (int i = 0; i < dbBean.getInitConnections(); i++) {
            // 2.创建Connection连接
            Connection newConnection = newConnection();
            if (newConnection != null) {
                // 3.存放在freeConnection集合
                freeConnection.add(newConnection);
            }
        }
    }

    // 创建Connection连接
    private synchronized Connection newConnection() {

        try {
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(),
                    dbBean.getPassword());
            countConnect++;
            return connection;
        } catch (Exception e) {
            return null;
        }

    }


    public Connection getConnection() {
        return null;
    }

    public void releaseConnection(Connection connection) {

    }
}
