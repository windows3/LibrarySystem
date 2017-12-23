package com.windows3.biz.impl;

import java.util.List;

import com.windows3.biz.RecordBiz;
import com.windows3.dao.RecordDao;
import com.windows3.dao.impl.RecordDaoImpl;
import com.windows3.entity.Record;

public class RecordBizImpl implements RecordBiz {
	private static RecordDao recordDao = new RecordDaoImpl();

	@Override
	public List<Record> queryRecordAll() {

		return recordDao.queryAll();
	}

	@Override
	public List<Record> queryRecordByUid(int uid) {
		return recordDao.queryRecordByUid(uid);
	}

	@Override
	public List<Record> queryRecordByBid(int bid) {

		return recordDao.queryRecordByBid(bid);
	}

	@Override
	public List<Record> queryRecordByUid_Bid(int uid, int bid) {
		
		return recordDao.queryRecordByUid_Bid(uid, bid);
	}

	@Override
	public boolean addRecord(int uid,int bid) {
		if(uid<1||bid<1) {
			return false;
		}
		Record record=new Record(uid,bid);
		return recordDao.addRecord(record);
	}

	@Override
	public List<Record> queryRecordByUidUnreturned(int uid) {
		
		return recordDao.queryRecordByBidUnreturned(uid);
	}

	@Override
	public List<Record> queryRecordByUidReturned(int uid) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordByBidReturned(uid);
	}

}
