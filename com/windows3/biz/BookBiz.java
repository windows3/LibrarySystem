package com.windows3.biz;

import com.windows3.entity.Book;

public interface BookBiz {

	// ע��
	boolean addBook(Book book);

	public boolean lendBook( String uname);

	boolean returnBook(int bid);//�������id��һ����

	boolean returnBook(String uname);

	Book queryBookById(int bid);
	

}
