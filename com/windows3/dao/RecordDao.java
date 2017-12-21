package com.windows3.dao;

import java.util.List;

import com.windows3.entity.Record;


public interface RecordDao {
   
	boolean addRecord(Record record);//根据书查
	boolean delRecordByRid(int rid);//根据记录的id删
	boolean updateRecord(Record newRecord);//更新书的名字或者数量
	List<Record> queryRecordByUid(int uid);//根据用户id查
	List<Record> queryRecordByBid(int bid);//根据书id查
	List<Record> queryRecordByUid_Bid(int uid,int bid);//根据书id查
	List<Record> queryAll();//查询所有记录


	
}
