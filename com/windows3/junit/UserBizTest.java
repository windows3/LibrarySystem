package com.windows3.junit;


import org.junit.Test;

import com.windows3.biz.UserBiz;
import com.windows3.biz.impl.UserBizImpl;
import com.windows3.entity.User;

public class UserBizTest {

	@Test
	public void test1() {
		UserBiz userBiz=new UserBizImpl();
		User user=new User("zhangsan","12345678");
		boolean b=userBiz.register(user);
		if(b) {
			System.out.println(user);
		}
	}
	@Test
	public void test2() {
		UserBiz userBiz=new UserBizImpl();
		User user=userBiz.login("zhangsan", "12345678");
		if(user!=null) {
			System.out.println(user);
		}
	}
	@Test
	public void test3() {
		UserBiz userBiz=new UserBizImpl();
		System.out.println(userBiz.queryUserByUname("0"));
	}

}
