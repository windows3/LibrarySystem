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

public class UserDaoImpl implements UserDao {
	private File file = new File("user.txt");
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	@Override
	public boolean addUser(User user) {
		if (user == null) {
			return false;
		}
		// 从文件中读出所有的User
		List<User> uList = read();
		// 新增用户id+1
		if (uList.isEmpty()) {
			user.setId(1);
		} else {
			int newId = uList.get(uList.size() - 1).getId() + 1;
			user.setId(newId);
		}

		uList.add(user);
		return write(uList);
	}

	@Override
	public boolean delUserById(int uid) {

		return false;
	}

	@Override
	public boolean updateUser(User newUser) {
		return false;
		
	}

	@Override
	public User queryUserById(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User queryUserByName(String uname) {
		List<User> uList = read();
		for (User user : uList) {
			if (user.getName().equals(uname)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> queryAll() {
		List<User> uList = read();

		return uList;
	}

	/**
	 * 从user.dat中读出所有User
	 * 
	 * @return
	 */
	private List<User> read() {
		// 第一次运行时，文件不存在，会崩。走着瞧
		List<User> uList = new ArrayList<User>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			uList = (List<User>) ois.readObject();
		} catch (FileNotFoundException e) {
			// file.getParentFile().mkdirs();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return uList;
	}

	/**
	 * 将List写入文件
	 * 
	 * @param uList
	 * @return
	 */
	private boolean write(List<User> uList) {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(uList);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
