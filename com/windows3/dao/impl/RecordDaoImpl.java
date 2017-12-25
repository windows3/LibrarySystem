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

import com.windows3.dao.BookDao;
import com.windows3.dao.RecordDao;
import com.windows3.entity.Record;

public class RecordDaoImpl<T> extends BaseDao<T> implements RecordDao {

	public RecordDaoImpl() {
		file = new File("record.txt");
	}

	@Override
	public boolean addRecord(Record record) {
		if (record == null) {
			return false;
		}
		List<Record> uList = (List<Record>) read();
		if (uList.isEmpty()) {
			record.setRid(1);
			String lendTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
			record.setLendTime(lendTime);
			record.setReturnTime("未归还");
		} else {
			record.setRid(uList.get(uList.size() - 1).getRid() + 1);
			String lendTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
			record.setLendTime(lendTime);
			record.setReturnTime("未归还");
		}
		uList.add(record);
		return write((List<T>) uList);
	}
	@Override
	public boolean addRecordRenew(int uid, int bid, int numDays) {
		List<Record> uList = (List<Record>) read();
		if (uList.isEmpty()||uList==null) {
			return false;
		} else {
			for (Record record : uList) {
				if(record.getBid()==bid&&record.getUid()==uid&&record.getReturnTime().equals("未归还")) {
					record.setNumDays(record.getNumDays()+numDays);
				}
			}
		}	
		return write((List<T>) uList);
	}

	@Override
	public boolean delRecordByRid(int rid) {
		if (rid < 1) {
			return false;
		}
		// 从文件中读出所有的Book
		List<Record> uList = (List<Record>) read();
		// 新增记录rid+1
		if (uList.isEmpty()) {
			return false;
		} else {
			for (Record record2 : uList) {
				if (record2.getRid() == rid) {
					if (record2.getReturnTime().equals("未归还")) {
						return false;
					} else {
						uList.remove(record2);
					}
				}
			}
		}

		return write((List<T>) uList);
	}

	@Override
	public boolean updateRecordToReturn(Record newRecord) {
		if (newRecord == null) {
			return false;
		}
		// 从文件中读出所有的record
		List<Record> uList = (List<Record>) read();
		// 新增记录rid+1
		if (uList.isEmpty()) {
			return false;
		} else {
			for (Record record2 : uList) {
				if (record2.getBid() == newRecord.getBid() && record2.getUid() == newRecord.getUid()
						&& record2.getReturnTime().equals("未归还")) {
					String returnTime = new SimpleDateFormat("yyyyMMddhh").format(new Date());
					record2.setReturnTime(returnTime);
				}
			}
		}
		return write((List<T>) uList);
	}

	@Override
	public boolean updateRecord(Record newRecord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Record> queryRecordByUid(int uid) {
		if (uid < 1) {
			return null;
		}
		// 从文件中读出所有的Book
		List<Record> uList = (List<Record>) read();
		List<Record> uList2 = new ArrayList<Record>();
		// 新增记录rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if (record2.getUid() == uid)
					uList2.add(record2);
			}
		}
		return uList2;
	}

	@Override
	public List<Record> queryRecordByBid(int bid) {
		if (bid < 1) {
			return null;
		}
		// 从文件中读出所有的Book
		List<Record> uList = (List<Record>) read();
		List<Record> uList2 = new ArrayList<Record>();
		// 新增记录rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if (record2.getBid() == bid)
					uList2.add(record2);
			}
		}
		return uList2;
	}

	@Override
	public List<Record> queryRecordByBidUnreturned(int uid) {
		if (uid < 1) {
			return null;
		}
		// 从文件中读出所有的Book
		List<Record> uList = (List<Record>) read();
		List<Record> uList2 = new ArrayList<Record>();
		// 新增记录rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if (record2.getUid() == uid && record2.getReturnTime().equals("未归还"))
					uList2.add(record2);
			}
		}
		return uList2;
	}

	@Override
	public List<Record> queryRecordByBidReturned(int uid) {
		if (uid < 1) {
			return null;
		}
		// 从文件中读出所有的Book
		List<Record> uList = (List<Record>) read();
		List<Record> uList2 = new ArrayList<Record>();
		// 新增记录rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if (record2.getUid() == uid && !record2.getReturnTime().equals("未归还"))
					uList2.add(record2);
			}
		}
		return uList2;
	}

	@Override
	public List<Record> queryAll() {
		return (List<Record>) read();
	}

	@Override
	public List<Record> queryRecordByUid_Bid(int uid, int bid) {
		if (bid < 1 || uid < 1) {
			return null;
		}
		// 从文件中读出所有的record
		List<Record> uList = (List<Record>) read();
		List<Record> uList2 = new ArrayList<Record>();
		// 新增记录rid+1
		if (uList.isEmpty()) {
			return null;
		} else {
			for (Record record2 : uList) {
				if (record2.getBid() == bid && record2.getUid() == uid)
					uList2.add(record2);
			}
		}
		return uList2;
	}

	
}
