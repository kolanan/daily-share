package com.chinamobile.guava;

import lombok.Data;

@Data
public class DbBean {

    private String driverName = "com.mysql.jdbc.Driver";

    private String url = "jdbc:mysql://gz-cynosdbmysql-grp-28h94h3n.sql.tencentcdb.com:28104/c8-site?autoReconnect=true&autoReconnectForPools=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    private String userName = "root";

    private String password = "Duanhaolan159357";

    // 连接池名字
    private String poolName = "duanhaolan";

    // 空闲池，最小连接数
    private int minConnections = 1;

    // 空闲池，最大连接数
    private int maxConnections = 10;

    // 初始化连接数
    private int initConnections = 5;

    // 重复获得连接的频率
    private long connTimeOut = 1000;

    // 最大允许的连接数，和数据库对应
    private int maxActiveConnections = 100;

    // 连接超时时间，默认20分钟
    private long connectionTimeOut = 1000 * 60 * 20;
}
