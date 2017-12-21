package com.windows3.biz.impl;

import java.util.List;

import com.windows3.biz.RecordBiz;
import com.windows3.dao.RecordDao;
import com.windows3.dao.impl.RecordDaoImpl;
import com.windows3.entity.Record;

public class RecordBizImpl implements RecordBiz {
	private static RecordDao recordDao = null;

	@Override
	public List<Record> queryRecordAll() {
		recordDao = new RecordDaoImpl();

		return recordDao.queryAll();
	}

	@Override
	public List<Record> queryRecordByUid(int uid) {
		recordDao = new RecordDaoImpl();

		return recordDao.queryRecordByUid(uid);
	}

	@Override
	public List<Record> queryRecordByBid(int bid) {
		recordDao = new RecordDaoImpl();

		return recordDao.queryRecordByBid(bid);
	}

	@Override
	public List<Record> queryRecordByUid_Bid(int uid, int bid) {
		// TODO Auto-generated method stub
		return null;
	}

}
