package com.windows3.dao;

import java.util.List;

import com.windows3.entity.User;

public interface UserDao {
   
	boolean addUser(User user);//�����û���
	boolean delUserById(int uid);//�����û�idɾ
	boolean updateUser(User newUser);//�����û������ֻ�������
	User queryUserById(int uid);//�����û�id��
	User queryUserByName(String uname);//�����û����ֲ�
	List<User> queryAll();//��ѯ�����û�
	
}
