package com.windows3.dao;

import java.util.List;

import com.windows3.entity.User;

public interface UserDao {
   
	boolean addUser(User user);//�����û���
	boolean updateUser(User newUser);//�����û������ֻ�������
	User queryUserById(int uid);//�����û�id��
	User queryUserByName(String uname);//�����û����ֲ�
	List<User> queryAll();//��ѯ�����û�
	boolean updateMoneyByBname(String bname,int numDays);//�����û��Ļ���10��
	boolean queryUserByUname_Money(String name);
	boolean queryUserByUname_Status(String uname);//��ѯ�ǲ���1
	boolean updateStatus(int uid, int i);//ָ������uid״̬���0��1
	boolean rechargeUserByUname(int uid, int money);//ָ���û���ֵnum����
	
	
}
