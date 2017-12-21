package com.windows3.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.windows3.dao.BookDao;
import com.windows3.dao.impl.BookDaoImpl;
import com.windows3.entity.Book;

public class BookDaoTest {

	@Test
	public void test() {
		BookDao bookDao=new BookDaoImpl();
		Book book=new Book("1",3);
		
		System.out.println(bookDao.addBook(book));
		
	}
	@Test
	public void test2() {
		BookDao bookDao=new BookDaoImpl();
		Book book=new Book("1",3);
		bookDao.addBook(book);
		System.out.println(bookDao.updateBook(book));
		System.out.println(bookDao.queryBookByName("1"));
		
	}
	@Test
	public void test3() {
		BookDao bookDao=new BookDaoImpl();
		
		Book book=bookDao.queryBookById(1);
		System.out.println(book);
		
		
	}
}
