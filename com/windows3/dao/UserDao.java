package com.windows3.dao;

import java.util.List;

import com.windows3.entity.User;

public interface UserDao {
   
	boolean addUser(User user);//根据用户查
	boolean updateUser(User newUser);//更新用户的名字或者密码
	User queryUserById(int uid);//根据用户id查
	User queryUserByName(String uname);//根据用户名字查
	List<User> queryAll();//查询所有用户
	boolean updateMoneyByBname(String bname,int numDays);//减少用户的积分10分
	boolean queryUserByUname_Money(String name);
	boolean queryUserByUname_Status(String uname);//查询是不是1
	boolean updateStatus(int uid, int i);//指定冻结uid状态变成0或1
	boolean rechargeUserByUname(int uid, int money);//指定用户充值num积分
	
	
}
