package com.windows3.biz;

import java.util.List;

import com.windows3.entity.Book;

public interface BookBiz {

	// 注册
	boolean addBook(Book book);

	public boolean lendBook( String uname);

	boolean returnBook(int bid);//根据书的id还一本书

	boolean returnBook(String uname);

	Book queryBookByBid(int bid);

	List<Book> queryByStatus(int status);

	boolean lendBook(int bid);

	int queryBookByBname(String bname);
	

}
