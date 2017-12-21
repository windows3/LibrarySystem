package com.windows3.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.windows3.dao.RecordDao;
import com.windows3.entity.Record;
import com.windows3.entity.Record;
import com.windows3.entity.Record;

public class RecordDaoImpl implements RecordDao {
	private File file = new File("record.txt");
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	@Override
	public boolean addRecord(Record record) {
		if (record == null) {
			return false;
		}
		// ���ļ��ж������е�Book
		List<Record> uList = read();
		// ������¼rid+1
		String lendTime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		record.setLendTime(lendTime);
		record.setReturnTime("0");

		if (uList.isEmpty()) {
			record.setRid(1);
		} else {
			int newId = uList.get(uList.size() - 1).getRid() + 1;
			record.setRid(newId);
		}

		uList.add(record);
		return write(uList);
	}

	@Override
	public boolean delRecordByRid(int rid) {
		if (rid<1) {
			return false;
		}
		// ���ļ��ж������е�Book
		List<Record> uList = read();
		// ������¼rid+1
		if (uList.isEmpty()) {
			return false;
		} else {
			for (Record record2 : uList) {
				if(record2.getRid()==rid)
					uList.remove(record2);
			}
		}
		return write(uList);
	}

	@Override
	public boolean updateRecord(Record newRecord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Record> queryRecordByUid(int uid) {
		if (uid<1) {
			return null;
		}
		// ���ļ��ж������е�Book
		List<Record> uList = read();
		List<Record> uList2 = new ArrayList<Record>();
		// ������¼rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if(record2.getUid()==uid)
					uList2.add(record2);
			}
		}
		return uList2;
	}

	@Override
	public List<Record> queryRecordByBid(int bid) {
		if (bid<1) {
			return null;
		}
		// ���ļ��ж������е�Book
		List<Record> uList = read();
		List<Record> uList2 = new ArrayList<Record>();
		// ������¼rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if(record2.getBid()==bid)
					uList2.add(record2);
			}
		}
		return uList2;
	}

	@Override
	public List<Record> queryAll() {
		return read();
	}
	@Override
	public List<Record> queryRecordByUid_Bid(int uid, int bid) {
		if (bid<1||uid<1) {
			return null;
		}
		// ���ļ��ж������е�Book
		List<Record> uList = read();
		List<Record> uList2 = new ArrayList<Record>();
		// ������¼rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if(record2.getBid()==bid&&record2.getUid()==uid)
					uList2.add(record2);
			}
		}
		return uList2;
	}
	/**
	 * ��record.txt�ж�������record
	 * 
	 * @return
	 */
	private List<Record> read() {
		// ��һ������ʱ���ļ������ڣ����
		List<Record> uList = new ArrayList<Record>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			uList = (List<Record>) ois.readObject();
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
	private boolean write(List<Record> uList) {
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
