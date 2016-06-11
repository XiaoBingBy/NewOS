package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 工具类
 * @author XiaoBingBy
 *
 */
public class JdbcUtils {
	
	private static DataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 返回 DataSource 对象 数据库连接池
	 * @return dataSource = new ComboPooledDataSource(); 数据库连接池
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 从数据库连接池 拿一个连接
	 * @return
	 * @throws SQLException 获取连接异常 抛出
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}