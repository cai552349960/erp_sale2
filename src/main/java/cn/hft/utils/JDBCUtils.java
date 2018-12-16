package cn.hft.utils;



/*
	1. 声明静态数据源成员变量
	2. 创建连接池对象
	3. 定义公有的得到数据源的方法
	4. 定义得到连接对象的方法
	5. 定义关闭资源的方法
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    /**
     *  * 获取资源
     */
    public static Connection getConnection() throws SQLException {
        return new JdbcConnection().getConnection();
    }
    /**
     *  * 关闭资源
     *  * @param resultSet 查询返回的结果集，没有为空
     *  * @param statement   
     *  * @param connection
     */
    public static void close(ResultSet resultSet, Statement statement,
                             Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resultSet = null;
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
