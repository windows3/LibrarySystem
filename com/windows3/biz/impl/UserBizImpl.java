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
		if (u == null) {// 用户不存在，用户名合法
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
			return null;// 用户名
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
	public boolean updateMoneyByBname(String uname,int numDays) {
		if(uname==null||numDays<1) {
			return false;
		}
		return userDao.updateMoneyByBname(uname,numDays);
	}

	@Override
	public boolean queryUserByUname_Money(String name) {
		if(name==null) {
			return false;
		}
		return userDao.queryUserByUname_Money(name);
	}

	@Override
	public boolean queryUserByUname_Status(String uname) {
		if(uname==null) {
			return false;
		}
		return userDao.queryUserByUname_Status(uname) ;
	}

	@Override
	public boolean updateStatus(int uid, int i) {
		if(uid<0||i<0) {
			return false;
		}
		return userDao.updateStatus(uid,i);//指定冻结uid状态变成0或1
	}

	@Override
	public boolean rechargeUserByUname(int uid, int money) {
		if(uid<0||money<0) {
			return false;
		}
		return userDao.rechargeUserByUname(uid,money);//指定用户充值num积分
	}

	@Override
	public List<User> queryUserAll() {
		
		return userDao.queryAll();
	}

	@Override
	public boolean queryUserByMoney_NumMoneys(int uid,int numDays) {
		if(uid<0||numDays<0) {
			return false;
		}
		
		return userDao.queryUserByMoney_NumMoneys(uid,numDays);
	}
}
