package com.windows3.biz;

import com.windows3.entity.User;

public interface UserBiz {

	// ע��
	boolean register(User user);

	// ��¼
	User login(String uname, String password);

	int queryUserByUname(String uname);

	User queryUserByUid(int uid);

	boolean updateMoneyByBname(String bname,int numDays);//�����û��Ļ���10��

	boolean queryUserByUname_Money(String name);

	boolean queryUserByUname_Status(String uname);//���������ǲ��ǿ��Ե�½,״̬�ǲ���1

	boolean updateStatus(int uid, int i);//ָ������uid״̬���0��1

	boolean rechargeUserByUname(int uid,int money);//ָ���û���ֵnum����
}
