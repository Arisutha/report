/**
 * Created By Muhammad Suta
 */
package com.sutha.id.service;

import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.sutha.id.model.Book;

/**
 * @author sutaeni
 *
 */
public interface BookService {
	public void insertBook(Book book) throws Exception;

	public void updateBook(Book book) throws Exception;

	public void deleteBook(Book book) throws Exception;

	public Book getBook(Long id) throws Exception;

	public DataSet<Map<String, Object>> findBookWithDatatablesCriterias(
			DatatablesCriterias criterias) throws Exception;
	
	public List<Book> reportBook() throws Exception;

	public List<Book> listBook(int start, int length, String search)
			throws Exception;

	public Long getCountBook() throws Exception;

	public Long getCountBook(String search) throws Exception;
}
