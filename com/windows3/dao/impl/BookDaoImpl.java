package com.windows3.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.windows3.dao.BookDao;
import com.windows3.entity.Book;

public class BookDaoImpl<T> extends BaseDao<T> implements BookDao {
	
public BookDaoImpl() {
	file = new File("book.txt");
}
	@SuppressWarnings("unchecked")
	@Override
	public boolean addBook(Book book) {
		if (book == null) {
			return false;
		}
		// 从文件中读出所有的Book
	
		List<Book> uList = (List<Book>) read();
		// 新增书id+1
		if (uList.isEmpty()) {
			book.setBcount(0);
			book.setId(1);
			book.setStatus(1);
		} else {
			for (Book book2 : uList) {
				if (book.getName().equals(book2.getName())) {
					return false;
				}
			}
			book.setStatus(1);
			int newId = uList.get(uList.size() - 1).getId() + 1;
			book.setId(newId);
			book.setBcount(0);
		}

		uList.add(book);
		return write((List<T>) uList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean delBookById(int uid) {
		if (uid < 1)
			return false;

		List<Book> bList = (List<Book>) read();
		if(bList.isEmpty()) {
			return false;
		}
		if(bList.get(bList.size()-1).getId()==uid)
			return false;
		for (Book book : bList) {
			 
			if (book.getId() == uid) {
				if (book.getStatus() == 0) {
					return false;
				} else {
					bList.remove(book);
					break;
				}
			}
		}
		return write((List<T>) bList);
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean delBookByBname(String bname) {
		if (bname ==null)
			return false;
		List<Book> bList = (List<Book>) read();
		if(bList.isEmpty()) {
			return false;
		}
		for (Book book : bList) {
			if (book.getName().equals(bname)) {
				if (book.getStatus() == 0) {
					return false;
				} else {
					bList.remove(book);
					break;
				}
			}
		}
		return write((List<T>) bList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateBook(Book newBook) {
		if (newBook == null)
			return false;
		List<Book> bList = (List<Book>) read();
		for (Book book : bList) {
			if (book.getName().equals(newBook.getName())) {
				if (book.getStatus() == 1) {
					book.setStatus(0);
					book.setBcount(book.getBcount()+1);
				}
				else 
					book.setStatus(1);
			}
		}
		return write((List<T>) bList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Book queryBookByBid(int uid) {
		List<Book> bList = (List<Book>) read();
		for (Book book : bList) {
			if (book.getId() == uid) {
				return book;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Book queryBookByName(String bname) {
		if(bname==null) {
			return null;
		}
		List<Book> bList = (List<Book>) read();
		for (Book book : bList) {
			if (book.getName().equals(bname))
				return book;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> queryAll() {
		List<Book> bList = (List<Book>) read();
		return bList;
	}
	@SuppressWarnings("unchecked")
	@Override
    public List<Book> queryBookByBcount(int num){
		List<Book> bList = (List<Book>) read();
		if(bList.isEmpty()) {
			return null;
		}
		 Collections.sort(bList, new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				// TODO Auto-generated method stub
				return o2.getBcount()-o1.getBcount();
			}
		});
		 List<Book> uList2 =new ArrayList<Book>();
		 if(bList.size()<num) {
			 for(int i=0;i<bList.size();i++) {
				 uList2.add(bList.get(i));
			 }
		 }else {
			 for(int i=0;i<num;i++) {
				 uList2.add(bList.get(i));
			 }
		 }
		return uList2;//返回热搜前五
    	
    }
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> queryBookByStatus(int status) {
		if(status<1) {
			return null;
		}
		List<Book> bList = (List<Book>) read();
		List<Book> bList2 = new ArrayList<Book>();
		if(bList==null) {
			return null;
		}
		for (Book book : bList) {
			if (book.getStatus() == 1)
				bList2.add(book);
		}
			return bList2;
		
	}


}
