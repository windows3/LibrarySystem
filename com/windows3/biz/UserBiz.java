package com.windows3.biz;

import com.windows3.entity.User;

public interface UserBiz {

	// ×¢²á
	boolean register(User user);

	// µÇÂ¼
	User login(String uname, String password);

	int queryUserByUname(String uname);
}
