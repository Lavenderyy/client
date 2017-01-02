package com.myproject.dao;

import com.myproject.model.User;

public interface UserDao {
	public User findByUsername(String Username);
	public boolean addUser(User user);
	public boolean delUser(User user);
	public boolean modUser(User user);
	//public User findByName(String name);
}
