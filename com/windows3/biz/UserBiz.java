package com.windows3.biz;

import com.windows3.entity.User;

public interface UserBiz {

	// ע��
	boolean register(User user);

	// ��¼
	User login(String uname, String password);

	int queryUserByUname(String uname);
}
