package cn.hft.utils;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;




public class JdbcConnection {

    private  Connection conn;

    // 在静态代码块中加载src/jdbc.properties数据库配置文件
    {
        InputStream in = JdbcConnection.class.getResourceAsStream("/jdbc.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
            String driver = prop.getProperty("driverClassName");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            // 加载数据库驱动
            Class.forName(driver);
                conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /*
     *  * 获取数据库连接
     */

    public Connection getConnection() throws SQLException {
        return conn;
    }

}
