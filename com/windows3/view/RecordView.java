package com.windows3.view;


import com.windows3.biz.RecordBiz;

import com.windows3.biz.impl.RecordBizImpl;


public class RecordView {
	private static RecordBiz recordBiz=new RecordBizImpl(); 
	public  void queryRecordAll() {
		//获取所有记录
		System.out.println(recordBiz.queryRecordAll());
		
	}
	public  void queryRecordByUid(int uid) {	
		recordBiz.queryRecordByUid(uid);
	}
	
	public void queryRecordByBid(int uid) {
		recordBiz.queryRecordByBid(uid);
	}
	
	
}
