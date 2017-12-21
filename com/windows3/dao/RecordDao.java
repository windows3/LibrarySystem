package com.windows3.dao;

import java.util.List;

import com.windows3.entity.Record;


public interface RecordDao {
   
	boolean addRecord(Record record);//�������
	boolean delRecordByRid(int rid);//���ݼ�¼��idɾ
	boolean updateRecord(Record newRecord);//����������ֻ�������
	List<Record> queryRecordByUid(int uid);//�����û�id��
	List<Record> queryRecordByBid(int bid);//������id��
	List<Record> queryRecordByUid_Bid(int uid,int bid);//������id��
	List<Record> queryAll();//��ѯ���м�¼


	
}
