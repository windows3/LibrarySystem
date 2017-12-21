package com.windows3.dao;

import java.util.List;

import com.windows3.entity.User;

public interface UserDao {
   
	boolean addUser(User user);//根据用户查
	boolean delUserById(int uid);//根据用户id删
	boolean updateUser(User newUser);//更新用户的名字或者密码
	User queryUserById(int uid);//根据用户id查
	User queryUserByName(String uname);//根据用户名字查
	List<User> queryAll();//查询所有用户
	
}
