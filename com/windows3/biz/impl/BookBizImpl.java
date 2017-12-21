package com.windows3.biz.impl;

import com.windows3.biz.BookBiz;
import com.windows3.dao.BookDao;
import com.windows3.dao.impl.BookDaoImpl;
import com.windows3.entity.Book;

public class BookBizImpl implements BookBiz {
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public boolean addBook(Book book) {
		if (book == null) {
			return false;
		}

		Book u = bookDao.queryBookByName(book.getName());
		if (u == null) {// 用户不存在，用户名合法
			return bookDao.addBook(book);
		} else {
			return false;
		}
	}

	@Override
	public boolean lendBook(String uname) {
		if (uname == null)
			return false;
		else {
			Book book = bookDao.queryBookByName(uname);
			if (book == null) {
				return false;
			} else {
				// 从biz层拿个方法用来更新书本的数量，一次一本
				boolean flag = bookDao.updateBook(book);
				if (flag)
					return true;
				else
					return false;
			}
		}
	}

	@Override
	public boolean returnBook(String uname) {
		if (uname == null)
			return false;
		else {
			Book book = bookDao.queryBookByName(uname);
			if (book == null) {
				return false;
			} else {
				// 从biz层拿个方法用来更新书本的数量，一次增加一本
				boolean flag = bookDao.updateBook2(book);
				if (flag)
					return true;
				else
					return false;
			}
		}
	}

	@Override
	public boolean returnBook(int bid) {
		if (bid<1)
			return false;
		else {
			Book book = bookDao.queryBookById(bid);
			if (book == null) {
				return false;
			} else {
				// 从biz层拿个方法用来更新书本的数量，一次增加一本
				boolean flag = bookDao.updateBook2(book);
				if (flag)
					return true;
				else
					return false;
			}
		}
	}
	@Override
	public Book queryBookById(int bid) {
		if(bid<1) {
			return null;
		}
		Book book=bookDao.queryBookById(bid);
		
		return book;

		
	}
}
