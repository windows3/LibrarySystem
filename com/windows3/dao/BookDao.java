package com.windows3.dao;

import java.util.List;

import com.windows3.entity.Book;


public interface BookDao {
   
	boolean addBook(Book book);//根据书查
	boolean delBookById(int uid);//根据书id删
	boolean updateBook(Book newBook);//更新书的名字或者数量
	Book queryBookByBid(int uid);//根据书id查
	Book queryBookByName(String uname);//根据书名字查
	List<Book> queryAll();//查询所有书
	List<Book> queryBookByStatus(int status);
	boolean delBookByBname(String bname);
	List<Book> queryBookByBcount(int num);//前几热借


	
}
