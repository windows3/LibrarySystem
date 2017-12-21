package com.windows3.biz;

import java.util.List;

import com.windows3.entity.Book;

public interface BookBiz {

	// ע��
	boolean addBook(Book book);

	public boolean lendBook( String uname);

	boolean returnBook(int bid);//�������id��һ����

	boolean returnBook(String uname);

	Book queryBookByBid(int bid);

	List<Book> queryByStatus(int status);

	boolean lendBook(int bid);

	int queryBookByBname(String bname);
	

}
