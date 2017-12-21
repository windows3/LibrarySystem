package com.windows3.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.windows3.dao.BookDao;
import com.windows3.entity.Book;
import com.windows3.entity.User;

public class BookDaoImpl implements BookDao {
	private File file = new File("book.txt");
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	@Override
	public boolean addBook(Book book) {
		if (book == null) {
			return false;
		}
		// 从文件中读出所有的Book
		List<Book> uList = read();
		// 新增书id+1
		if (uList.isEmpty()) {
			book.setId(1);
		} else {
			for (Book book2 : uList) {
				if(book.getName().equals(book2.getName())) {
					return false;
				}
			}
			book.setStatus(1);
			int newId = uList.get(uList.size() - 1).getId() + 1;
			book.setId(newId);
		}

		uList.add(book);
		return write(uList);
	}

	@Override
	public boolean delBookById(int uid) {
		if(uid<1)
			return false;
		
		List<Book> uList = read();
		for (Book book : uList) {
			if (book.getId()==uid) {
				if(book.getStatus()==0) {
				     return false;
				}else {
					 uList.remove(book);
					 break;
				}
			}
		}
		return write(uList);
	}

	@Override
	public boolean updateBook(Book newBook) {
		if(newBook==null)
			return false;
		List<Book> uList = read();
		for (Book book : uList) {
			if (book.getName().equals(newBook.getName())) {
				if(book.getStatus()==1) 
				      book.setStatus(0);
				else
					 book.setStatus(1);
			}
		}
		return write(uList);
	}

	@Override
	public Book queryBookById(int uid) {
		List<Book> uList = read();
		for (Book book : uList) {
			if (book.getId()==uid) {
				return book;
			}
		}
		return null;
	}

	@Override
	public Book queryBookByName(String bname) {
		List<Book> uList = read();
		for (Book book : uList) {
			if (book.getName().equals(bname))
				return book;	
		}
		return null;
	}

	@Override
	public List<Book> queryAll() {
		List<Book> uList = read();
		return uList;
	}

	/**
	 * 从book.txt中读出所有book
	 * 
	 * @return
	 */
	private List<Book> read() {
		// 第一次运行时，文件不存在，会崩
		List<Book> uList = new ArrayList<Book>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			uList = (List<Book>) ois.readObject();
		} catch (FileNotFoundException e) {
			// file.getParentFile().mkdirs();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return uList;
	}

	/**
	 * 将List写入文件
	 * 
	 * @param uList
	 * @return
	 */
	private boolean write(List<Book> uList) {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(uList);
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		return false;
	}


}
