package com.windows3.biz;

import com.windows3.entity.Book;

public interface BookBiz {

	// 注册
	boolean addBook(Book book);

	public boolean lendBook( String uname);

	boolean returnBook(int bid);//根据书的id还一本书

	boolean returnBook(String uname);

	Book queryBookById(int bid);
	

}
