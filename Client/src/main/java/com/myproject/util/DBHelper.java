package com.myproject.util;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBHelper {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/client?useUnicode=true&characterEncoding=utf-8";
	private static String username="root";
	private static String password="";
	/*
	 * 打开数据库连�?
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);//注册mysql驱动
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
