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
	public boolean addRecord(int uid,int bid,int numDays) {
		if(uid<1||bid<1||numDays<1) {
			return false;
		}
		Record record=new Record(uid,bid,numDays);
		return recordDao.addRecord(record);
	}

	@Override
	public List<Record> queryRecordByUidUnreturned(int uid) {
		if(uid<1) {
			return null;
		}
		return recordDao.queryRecordByBidUnreturned(uid);
	}

	@Override
	public List<Record> queryRecordByUidReturned(int uid) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordByBidReturned(uid);
	}

	@Override
	public boolean updateRecord(int uid, int bid) {
		if(uid<1||bid<1) {
			return false;
		}
		Record record=new Record(uid,bid);
		return recordDao.updateRecordToReturn(record);
	}

	@Override
	public boolean addRecordRenew(int queryUserByUname, int bid, int numDays) {
		
		return recordDao.addRecordRenew(queryUserByUname,bid,numDays);
	}

	@Override
	public boolean queryRecordByUidBidToBoolean(int uid, int bid) {
		//找找这个人界的书是不是有这一本，是不是没换
		if(uid<1||bid<1) {
			return false;
		}else {
		List<Record> rList=recordDao.queryRecordByUid_Bid(uid, bid);
		if(rList==null||rList.isEmpty()) {
			return false;
		}else {
			for (Record record : rList) {
				if(record.getBid()==bid&&record.getUid()==uid&&record.getReturnTime().equals("未归还")) {
					return true;
				}
			}
				return false;
			}
		}
	}

}
