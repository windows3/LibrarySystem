package com.windows3.biz.impl;

import com.windows3.biz.UserBiz;
import com.windows3.dao.UserDao;
import com.windows3.dao.impl.UserDaoImpl;
import com.windows3.entity.User;

public class UserBizImpl implements UserBiz {
private UserDao userDao = new UserDaoImpl();
	
	@Override
	public boolean register(User user) {
		if(user == null){
			return false;
		}
		
		User u = userDao.queryUserByName(user.getName());
		if(u == null){// 用户不存在，用户名合法
			return userDao.addUser(user);
		}else{
			return false;
		}
		
	}

	@Override
	public User login(String uname, String password) {
		if(uname == null || password == null){
			return null;
		}
		
		User u = userDao.queryUserByName(uname);
		if(u == null){
			return null;// 用户名
		}else{
			if(u.getPassword().equals(password)){
				return u;
			}else {	
				return null;
			}
		}
	}

	@Override
	public int queryUserByUname(String uname) {
		return userDao.queryUserByName(uname).getId();
	}
}
