package com.windows3.biz;

import com.windows3.entity.User;

public interface UserBiz {

	// 注册
	boolean register(User user);

	// 登录
	User login(String uname, String password);

	int queryUserByUname(String uname);

	User queryUserByUid(int uid);

	boolean updateMoneyByBname(String bname,int numDays);//减少用户的积分10分

	boolean queryUserByUname_Money(String name);

	boolean queryUserByUname_Status(String uname);//查查这个人是不是可以登陆,状态是不是1

	boolean updateStatus(int uid, int i);//指定冻结uid状态变成0或1

	boolean rechargeUserByUname(int uid,int money);//指定用户充值num积分
}
