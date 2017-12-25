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


public class BaseDao<T> {
	protected File file =null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	protected List<T> read() {
		// ��һ������ʱ���ļ������ڣ����
		List<T> uList = new ArrayList<T>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			uList = (List<T>) ois.readObject();
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
	protected boolean write(List<T> uList) {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(uList);
			return true;
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
