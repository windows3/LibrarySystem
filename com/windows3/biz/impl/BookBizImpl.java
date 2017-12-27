package com.windows3.biz.impl;

import java.util.List;

import com.windows3.biz.BookBiz;
import com.windows3.dao.impl.BookDaoImpl;
import com.windows3.entity.Book;

public class BookBizImpl implements BookBiz {
	@SuppressWarnings("rawtypes")
	private BookDaoImpl bookDao = new BookDaoImpl();

	@Override
	public boolean addBook(Book book) {
		if (book == null) {
			return false;
		}
		Book u = bookDao.queryBookByName(book.getName());
		if (u == null) // �������ڣ������Ϸ�
			return bookDao.addBook(book);
		else
			return false;

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
				// ��biz���ø��������������鱾״̬
				boolean flag = bookDao.updateBook(book);
				if (flag)
					return true;
				else
					return false;
			}
		}
	}

	@Override
	public boolean lendBook(int bid) {
		if (bid < 1)
			return false;
		else {
			Book book = bookDao.queryBookByBid(bid);
			if (book == null) {
				return false;
			} else {
				// ��biz���ø��������������鱾״̬
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
				// ��biz���ø��������������鱾״̬
				boolean flag = bookDao.updateBook(book);
				if (flag)
					return true;
				else
					return false;
			}
		}
	}

	@Override
	public boolean returnBook(int bid) {
		if (bid < 1)
			return false;
		else {
			Book book = bookDao.queryBookByBid(bid);
			if (book == null) {
				return false;
			} else {
				// ��biz���ø��������������鱾��������һ������һ��
				boolean flag = bookDao.updateBook(book);
				if (flag)
					return true;
				else
					return false;
			}
		}
	}

	@Override
	public Book queryBookByBid(int bid) {
		if (bid < 1) {
			return null;
		}
		Book book = bookDao.queryBookByBid(bid);

		return book;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> queryByStatus(int status) {
		List<Book> uList = bookDao.queryBookByStatus(status);
		return uList;
	}

	@Override
	public int queryBookByBname(String bname) {
	
		Book book=bookDao.queryBookByName(bname);
		if(book==null) {
			return -1;
		}
		return book.getId();
	}

	@Override
	public boolean delBookByBid(int bid) {

		return bookDao.delBookById(bid);

	}

	@Override
	public boolean delBookByBname(String bname) {
		// TODO Auto-generated method stub
		return bookDao.delBookByBname(bname);
	}

	@Override
	public List<Book> queryByBcount(int num) {
		// TODO Auto-generated method stub
		return bookDao.queryBookByBcount(num);
	}

	@Override
	public List<Book> queryBookAll() {
		
		return bookDao.queryAll();
	}
}
