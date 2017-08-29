/**
 * Created By Muhammad Suta
 */
package com.sutha.id.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.sutha.id.dao.BookDao;
import com.sutha.id.model.Book;
import com.sutha.id.service.BookService;

/**
 * @author sutaeni
 *
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	public void insertBook(Book book) throws Exception {
		book.setDel(false);;
		book.setCreateTime(new Date());
		this.bookDao.insertBook(book);
	}

	@Override
	public void updateBook(Book book) throws Exception {
		Book getBook = this.bookDao.getBook(book.getId());
		getBook.setId(book.getId());
		getBook.setName(book.getName());
		getBook.setAuthor(book.getAuthor());
		getBook.setPrice(book.getPrice());
		getBook.setReleaseDate(book.getReleaseDate());
		getBook.setStock(book.getStock());
		getBook.setUpdateTime(new Date());
		this.bookDao.updateBook(getBook);
	}

	@Override
	public void deleteBook(Book book) throws Exception {
		Book getBook = this.bookDao.getBook(book.getId());
		getBook.setDel(true);
		getBook.setDelTime(new Date());
		this.bookDao.deleteStatus(getBook);
	}

	@Override
	public Book getBook(Long id) throws Exception {
		Book getBook = this.bookDao.getBook(id);
		return getBook;
	}

	@Override
	public DataSet<Map<String, Object>> findBookWithDatatablesCriterias(
			DatatablesCriterias criterias) throws Exception {
		List<Book> data = this.bookDao.listBook(criterias.getStart(),
				criterias.getLength(), criterias.getSearch());
		List<Map<String, Object>> dataBook = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = null;
		for (Book book : data) {
			map = new HashMap<String, Object>();
			map.put("id", book.getId());
			map.put("name", book.getName());
			map.put("author", book.getAuthor());
			map.put("releaseDate", book.getReleaseDate());
			map.put("price", book.getPrice());
			map.put("stock", book.getStock());
			dataBook.add(map);
		}
		
		Long totalRecords = this.bookDao.getCountBook();
		Long totalDisplayRecords;
		if(criterias.getSearch() != null && !criterias.getSearch().isEmpty()){
			totalDisplayRecords = this.bookDao.getCountBook(criterias.getSearch()); 
		}else{
			totalDisplayRecords = totalRecords;
		}
		return new DataSet<Map<String,Object>>(dataBook, totalRecords, totalDisplayRecords);
	}

	@Override
	public List<Book> listBook(int start, int length, String search)
			throws Exception {
		return this.bookDao.listBook(start, length, search);
	}

	@Override
	public Long getCountBook() throws Exception {
		return this.bookDao.getCountBook();
	}

	@Override
	public Long getCountBook(String search) throws Exception {
		return this.bookDao.getCountBook(search);
	}

	@Override
	public List<Book> reportBook() throws Exception {
		return this.bookDao.reportBook();
	}

}
