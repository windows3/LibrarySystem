package com.windows3.junit;


import org.junit.Test;

import com.windows3.biz.BookBiz;
import com.windows3.biz.impl.BookBizImpl;

public class BookBizTest {

	@Test
	public void test() {
		BookBiz b=new BookBizImpl();
		System.out.println(b.queryByStatus(1));
	}

}
