package com.windows3.junit;


import org.junit.Test;

import com.windows3.dao.RecordDao;
import com.windows3.dao.impl.RecordDaoImpl;
import com.windows3.entity.Record;

public class RecordDaoText {
	
  @Test
  public void test1() {
//	  String lendTime=new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new Date());
//	  System.out.println(lendTime);
	  RecordDao r=new RecordDaoImpl();
//	 System.out.println(r.addRecord(new Record(1,1)));
//	 System.out.println(r.addRecord(new Record(2,1)));
	  System.out.println(r.addRecord(new Record(3,1)));
//	  Record record=new Record(1,1);
//	  System.out.println(r.addRecord(record));
	 
  }
}
