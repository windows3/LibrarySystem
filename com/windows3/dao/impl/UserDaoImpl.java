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
		// ���ļ��ж������е�User
		List<User> uList = read();
		// �����û�id+1
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
	 * ��user.dat�ж�������User
	 * 
	 * @return
	 */
	private List<User> read() {
		// ��һ������ʱ���ļ������ڣ������������
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
	 * ��Listд���ļ�
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
