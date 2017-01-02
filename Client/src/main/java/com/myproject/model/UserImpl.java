package com.myproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.myproject.dao.UserDao;
import com.myproject.util.DBHelper;

public class UserImpl implements UserDao{
	private QueryRunner runner;
	private Connection connection;
	public UserImpl() {
		// TODO Auto-generated constructor stub
		runner = new QueryRunner();
		connection = DBHelper.getConnection();
	}
	
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = ?";
		ResultSetHandler<User> rsHandler = new BeanHandler<User>(User.class);
		User user = null;
		try {
			user = runner.query(connection,sql, rsHandler,username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	

	@Override
	public boolean addUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into user(username,password) " + "values(?,?)";

		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.executeUpdate();
			System.out.println("add a new user.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断用户是否已经保存过了
	 * @param openid
	 * @return
	 */
	public boolean isExist(String username){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from user where username = ?";

		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("exist");
				return true;
			}else{
				System.out.println("not exist");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			// 释放数据集对�?
			if (rs != null) {
				try {
				    rs.close();
					rs = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 当用户访问商店的时�?�就进行更新信息操作
	 * 包括nickname，headimgurl
	 * 前提是应经关注了公众�?
	 * 
	 */
	public void refreshUser(User userTemp,String username){
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update user set password=? where username =?";

		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setString(1, userTemp.getPassword());
			stmt.setString(2, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Override
	public boolean delUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	public void closeConnection() {
		try {
			DbUtils.close(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
