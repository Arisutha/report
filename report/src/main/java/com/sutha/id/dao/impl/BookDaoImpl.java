/**
 * Created By Muhammad Suta
 */
package com.sutha.id.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sutha.id.dao.BookDao;
import com.sutha.id.model.Book;

/**
 * @author sutaeni
 *
 */
@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void insertBook(Book book) throws Exception {
		this.getCurrentSession().save(book);
	}

	@Override
	public void updateBook(Book book) throws Exception {
		this.getCurrentSession().update(book);
	}

	@Override
	public void deleteStatus(Book book) throws Exception {
		this.getCurrentSession().delete(book);
	}

	@Override
	public Book getBook(Long id) throws Exception {
		Query query = this.getCurrentSession().createQuery("FROM Book tb where tb.del = 0 AND tb.id = :id").setParameter("id", id);		
		if(query.list().size() > 0){
			return (Book) query.list().get(0);
		}else{
			return new Book();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBook(int start, int length, String search)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		if (search != null && !search.isEmpty()) {
			sql.append(" AND str(b.id) LIKE :search");
			return this
					.getCurrentSession()
					.createQuery(
							"SELECT b FROM Book b WHERE b.del = false" + sql)
					.setParameter("search", "%" + search + "%").list();
		} else {
			return this.getCurrentSession()
					.createQuery("FROM Book b WHERE b.del = false")
					.setFirstResult(start).setMaxResults(length).list();
		}
	}

	@Override
	public Long getCountBook() throws Exception {
		String count = this
				.getCurrentSession()
				.createSQLQuery(
						"SELECT COUNT(*) FROM tb_book b WHERE b.status_delete = 0")
				.list().get(0).toString();
		Long jumlah = Long.parseLong(count);
		return jumlah;
	}

	@Override
	public Long getCountBook(String search) throws Exception {
		StringBuilder sql = new StringBuilder();

		sql.append(" AND b.id LIKE :search");
		String count = this
				.getCurrentSession()
				.createSQLQuery(
						"SELECT COUNT(*) FROM tb_book b WHERE b.status_delete = 0"
								+ sql)
				.setParameter("search", "%" + search + "%").list().get(0)
				.toString();
		return Long.parseLong(count);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> reportBook() throws Exception {
		// TODO Auto-generated method stub
		return this.getCurrentSession()
				.createQuery("FROM Book").list();

	}

}
