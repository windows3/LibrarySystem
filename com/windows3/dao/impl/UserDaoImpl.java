package com.windows3.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.windows3.dao.UserDao;
import com.windows3.entity.User;

public class UserDaoImpl<T> extends BaseDao<T> implements UserDao {

	public UserDaoImpl() {
		file = new File("user.txt");
	}

	@Override
	public boolean addUser(User user) {
		if (user == null) {
			return false;
		}
		// 从文件中读出所有的User
		List<User> uList = (List<User>) read();
		// 新增用户id+1
		if (uList.isEmpty()) {
			user.setId(1);
			user.setStatus(1);
			user.setMoney(100);// 初始100￥
		} else {
			int newId = uList.get(uList.size() - 1).getId() + 1;
			user.setId(newId);
			user.setMoney(100);// 初始100￥
			user.setStatus(1);
		}

		uList.add(user);
		return write((List<T>) uList);
	}

	@Override
	public boolean updateUser(User newUser) {
		return false;

	}

	@Override
	public boolean queryUserByUname_Money(String name) {
		if (name == null) {
			return false;
		}
		// 从文件中读出所有的User
		List<User> uList = (List<User>) read();
		// 新增用户id+1
		if (uList.isEmpty()) {
			return false;
		} else {
			for (User user2 : uList) {
				if (user2.getName().equals(name) && user2.getMoney() >50)
					return true;
			}
			return false;
		}
	}

	@Override
	public boolean updateMoneyByBname(String uname,int numDays) {// 减少用户的n倍积分10分
		// 从文件中读出所有的User
		List<User> uList = (List<User>) read();
		// 新增用户id+1
		if (uList.isEmpty()) {
			return false;
		} else {
			for (User user2 : uList) {
				if (user2.getName().equals(uname)) {
					int moneys=user2.getMoney() - numDays*10;
					if(moneys<0) { 
						return false;
					}else {
						user2.setMoney(moneys);						
					}
				}
			}
		}
		return write((List<T>) uList);
	}

	@Override
	public User queryUserById(int uid) {
		if (uid < 1) {
			return null;
		}
		// 从文件中读出所有的User
		List<User> uList = (List<User>) read();
		// 新增用户id+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (User user2 : uList) {
				if (user2.getId() == uid) {
					return user2;
				}
			}
		}
		return null;

	}

	@Override
	public User queryUserByName(String uname) {
		if (uname == null) {
			return null;
		}
		List<User> uList = (List<User>) read();
		if (uList.isEmpty()) {
			return null;
		}
		for (User user : uList) {
			if (user.getName().equals(uname)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public boolean queryUserByUname_Status(String uname) {
		if (uname == null) {
			return false;
		}
		List<User> uList = (List<User>) read();
		if (uList.isEmpty()) {
			return false;
		}
		for (User user : uList) {
			if (user.getStatus() == 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<User> queryAll() {
		List<User> uList = (List<User>) read();

		return uList;
	}

	@Override
	public boolean updateStatus(int uid, int i) {
		List<User> uList = (List<User>) read();
		if (uList.isEmpty())
			return false;
		else {
			for (User user : uList) {
				if (user.getId() == uid) {
					user.setStatus(i);
					
				}
			}
			return write((List<T>) uList);
		}
	}

	@Override
	public boolean rechargeUserByUname(int uid, int money) {
		if(uid<0||money<0) {
			return false;
		}
		List<User> uList = (List<User>) read();
		if (uList==null||uList.isEmpty())
			return false;
		else {
			for (User user : uList) {
				if (user.getId() == uid) {
					user.setMoney(money+user.getMoney());
					user.setStatus(1);
					
				}
			}
			return write((List<T>) uList);
		}
	}

	@Override
	public boolean queryUserByMoney_NumMoneys(int uid, int numDays) {//看看积分还够不够
		if(uid<0||numDays<0) {
			return false;
		}
		List<User> uList = (List<User>) read();
		if (uList==null||uList.isEmpty())
			return false;
		else {
			for (User user : uList) {
				if (user.getId() == uid&&user.getMoney()>numDays*10) {
					
					return true;
				}
			}
			return false;
		}
	}

//	/**
//	 * 从user.dat中读出所有User
//	 * 
//	 * @return
//	 */
//	private List<User> read() {
//		// 第一次运行时，文件不存在，会崩。走着瞧
//		List<User> uList = new ArrayList<User>();
//		try {
//			ois = new ObjectInputStream(new FileInputStream(file));
//			uList = (List<User>) ois.readObject();
//		} catch (FileNotFoundException e) {
//			// file.getParentFile().mkdirs();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (ois != null) {
//				try {
//					ois.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return uList;
//	}
//
//	/**
//	 * 将List写入文件
//	 * 
//	 * @param uList
//	 * @return
//	 */
//	private boolean write(List<User> uList) {
//		try {
//			oos = new ObjectOutputStream(new FileOutputStream(file));
//			oos.writeObject(uList);
//			return true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (oos != null) {
//				try {
//					oos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return false;
//	}

}
