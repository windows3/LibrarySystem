package com.windows3.biz;

import java.util.List;

import com.windows3.entity.Record;

public interface RecordBiz {

	List<Record> queryRecordAll();

	List<Record> queryRecordByUid(int uid);

	List<Record> queryRecordByBid(int bid);
	List<Record> queryRecordByUid_Bid(int uid,int bid);
    boolean addRecord(int uid,int bid);
}
