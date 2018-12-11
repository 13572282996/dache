package com.lczx.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/*
 * 连接工厂类
 * 描述：用于产生数据库连接Connection对象。在数据库的增、删、改、查
 * 的操作中，都需要获取与数据库的连接对象。从软件的可重用性考虑，可以
 * 把一部分封装成一个单独的类，专门用于产生Connection对象。
 */
public class ConnectionFactory {
	// 加载的驱动程序类名
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	// 连接数据库的URL
	//private static final String DB_URL = "jdbc:mysql://localhost:3306/yuntuwu?charcterEncoding=utf-8";
	private static String DB_URL = null;
	
	// 连接数据库的用户名
	private static String DB_USER = null;
	// 连接数据库的密码
	private static String DB_PASSWORD = null;
	
	static
	{
		loads();
	}
	private static Properties loads()
	{
		InputStream is = ConnectionFactory.class.getResourceAsStream("/definedweek.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			DB_PASSWORD = properties.getProperty("jdbc.password");
			DB_URL = properties.getProperty("jdbc.url");
			DB_USER = properties.getProperty("jdbc.username");
		} catch (IOException e) {
			System.out.println("不能读取属性文件. " +
				    "请确保db.properties在CLASSPATH指定的路径中");
			e.printStackTrace();
		}
		return properties;
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		//加载JDBC驱动程序
		try {
			Class.forName(DB_DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//创建数据库连接
		try {
			connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	public static void close(ResultSet resultSet,Statement statement,Connection connection) {
		close(resultSet);
		close(statement);
		close(connection);
	}
	
	public static void close(Statement statement,Connection connection) {
		close(statement);
		close(connection);
	}
	
	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

