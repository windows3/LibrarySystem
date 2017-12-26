package com.windows3.biz.impl;

import java.util.List;

import com.windows3.biz.UserBiz;
import com.windows3.dao.UserDao;
import com.windows3.dao.impl.UserDaoImpl;
import com.windows3.entity.User;

public class UserBizImpl implements UserBiz {
	private UserDaoImpl userDao = new UserDaoImpl();

	@Override
	public boolean register(User user) {
		if (user == null) {
			return false;
		}

		User u = userDao.queryUserByName(user.getName());
		if (u == null) {// �û������ڣ��û����Ϸ�
			return userDao.addUser(user);
		} else {
			return false;
		}

	}

	@Override
	public User login(String uname, String password) {
		if (uname == null || password == null) {
			return null;
		}

		User u = userDao.queryUserByName(uname);
		if (u == null) {
			return null;// �û���
		} else {
			if (u.getPassword().equals(password)) {
				return u;
			} else {
				return null;
			}
		}
	}

	@Override
	public int queryUserByUname(String uname) {
		User user = userDao.queryUserByName(uname);
		if (user == null)
			return -1;
		else

			return user.getId();
	}

	@Override
	public User queryUserByUid(int uid) {
		// TODO Auto-generated method stub
		return userDao.queryUserById(uid);
	}

	@Override
	public boolean updateMoneyByBname(String bname,int numDays) {
		if(bname==null||numDays<1) {
			return false;
		}
		return userDao.updateMoneyByBname(bname,numDays);
	}

	@Override
	public boolean queryUserByUname_Money(String name) {
		
		return userDao.queryUserByUname_Money(name);
	}

	@Override
	public boolean queryUserByUname_Status(String uname) {
		
		return userDao.queryUserByUname_Status(uname) ;
	}

	@Override
	public boolean updateStatus(int uid, int i) {
		
		return userDao.updateStatus(uid,i);//ָ������uid״̬���0��1
	}

	@Override
	public boolean rechargeUserByUname(int uid, int money) {
		// TODO Auto-generated method stub
		return userDao.rechargeUserByUname(uid,money);//ָ���û���ֵnum����
	}

	@Override
	public List<User> queryUserAll() {
		// TODO Auto-generated method stub
		return userDao.queryAll();
	}

	@Override
	public boolean queryUserByMoney_NumMoneys(int uid,int numDays) {
		
		return userDao.queryUserByMoney_NumMoneys(uid,numDays);
	}
}
