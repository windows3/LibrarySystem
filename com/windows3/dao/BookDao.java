package com.windows3.dao;

import java.util.List;

import com.windows3.entity.Book;


public interface BookDao {
   
	boolean addBook(Book book);//�������
	boolean delBookById(int uid);//������idɾ
	boolean updateBook(Book newBook);//����������ֻ�������
	Book queryBookById(int uid);//������id��
	Book queryBookByName(String uname);//���������ֲ�
	List<Book> queryAll();//��ѯ������


	
}
