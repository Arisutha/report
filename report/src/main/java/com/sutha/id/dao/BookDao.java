/**
 * Created By Muhammad Suta
 */
package com.sutha.id.dao;

import java.util.List;

import com.sutha.id.model.Book;

/**
 * @author sutaeni
 *
 */
public interface BookDao {
	public void insertBook(Book book) throws Exception;

	public void updateBook(Book book) throws Exception;

	public void deleteStatus(Book book) throws Exception;

	public Book getBook(Long id) throws Exception;
	
	public List<Book> reportBook() throws Exception;

	public List<Book> listBook(int start, int length, String search)
			throws Exception;

	public Long getCountBook() throws Exception;

	public Long getCountBook(String search) throws Exception;
}
