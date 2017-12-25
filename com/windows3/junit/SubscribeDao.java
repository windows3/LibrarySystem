package com.windows3.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.windows3.dao.impl.SubscribeDaoImpl;
import com.windows3.entity.Subscribe;

public class SubscribeDao {

	@Test
	public void test() {
		SubscribeDaoImpl<?> subDao=new SubscribeDaoImpl();
//		System.out.println(subDao.addSub(new Subscribe(1,1)));
		System.err.println(subDao.querySubAll());
	}

}
