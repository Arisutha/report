/**
 * Created By Muhammad Suta
 */
package com.sutha.id.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.sutha.id.model.Book;
import com.sutha.id.service.BookService;

/**
 * @author sutaeni
 *
 */
@Controller
public class MainController extends BaseController{

	private Log log = LogFactory.getLog(getClass());
	private Report report = new Report();
	private Map<String, Object> map = new HashMap<String, Object>();
	@Autowired
	private BookService bookService;
	
	@Autowired
	private DataSource dataSource;

	/* private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); */
	/* private Date date; */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model m) {
		return "index";
	}

	@RequestMapping(value = "/base/getAllBook", method = RequestMethod.GET)
	public @ResponseBody void getData(HttpServletRequest req,
			HttpServletResponse resp) {
		DatatablesCriterias criterias = DatatablesCriterias.getFromRequest(req);

		Long totalDisplayRecords = null;
		Long totalRecords = null;
		List<Book> data = null;
		try {
			/*
			 * data = this.bookService.listBook(0, 10, "");
			 */
			data = this.bookService.listBook(criterias.getStart(),
					criterias.getLength(), criterias.getSearch());
			totalRecords = this.bookService.getCountBook();
			if (criterias.getSearch() != null
					&& !criterias.getSearch().isEmpty()) {
				totalDisplayRecords = this.bookService.getCountBook(criterias
						.getSearch());
			} else {
				totalDisplayRecords = totalRecords;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", criterias.getDraw());
		map.put("data", data);
		map.put("totalRecords", totalRecords);
		map.put("totalDisplayRecords", totalDisplayRecords);
		this.encodeToJson(resp, map);
	}

	@RequestMapping(value = "/bookReport", method = RequestMethod.GET)
	public ModelAndView generateReport(ModelAndView view) {
		log.debug("--------------generate PDF report----------");
		List<Book> list = null;
		
		try {
			list = this.bookService.reportBook();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		Map<String, Object> parameterKey = new HashMap<String, Object>();
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);
		parameterKey.put("dataSource", jrDataSource);
		// pdfReport bean has ben declared in the jasper-views.xml file
		view = new ModelAndView("pdfReport", parameterKey);

		return view;

	}
	
	@RequestMapping(value = "/bookReportExcelPoi", method = RequestMethod.GET)
	public ModelAndView generateReportExcelPoi(ModelAndView view){
		List<Book> list = null;
		try {
			list = this.bookService.reportBook();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new ModelAndView("excelBookReport", "listBooks", list);
	}
	
	@RequestMapping(value = "/bookReportExcel", method = RequestMethod.GET)
	public ModelAndView generateReportExcel(ModelAndView view) {
		log.debug("--------------generate EXCEL report----------");
		List<Book> list = null;
		
		try {
			list = this.bookService.reportBook();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		Map<String, Object> parameterKey = new HashMap<String, Object>();
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);
		parameterKey.put("dataSource", jrDataSource);
		// pdfReport bean has ben declared in the jasper-views.xml file
		view = new ModelAndView("excelReport", parameterKey);

		return view;

	}
	
	@RequestMapping(value={"/bookReportPdf"}, method = RequestMethod.GET)
	public void generateReportWithConnection(HttpServletRequest request, HttpServletResponse response, Model m){
		log.debug("-------------------generate PDF Report-----------------");
		try {
			String name = request.getParameter("nameReport");
			String mode = request.getParameter("mode");
			request.setAttribute("reportFile", name);
			request.setAttribute("mode", mode);
			this.report.exportToPDF(request, response, dataSource);		
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping(value = "/getAllBook", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> findAllForBook(
			HttpServletRequest request) {
		DatatablesResponse<Map<String, Object>> response = null;
		try {
			DatatablesCriterias criterias = DatatablesCriterias
					.getFromRequest(request);
			DataSet<Map<String, Object>> dataSet = this.bookService
					.findBookWithDatatablesCriterias(criterias);
			response = DatatablesResponse.build(dataSet, criterias);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return response;
	}
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertBook(HttpServletRequest req, HttpServletResponse response, @RequestBody String data){
		//konversi json to book model
		ObjectMapper mapper = new ObjectMapper();		
		try {
			Book book = mapper.readValue(data, Book.class);
			this.bookService.insertBook(book);
			map.put("message", "sukses menyimpan data");
			this.encodeToJson(response, map);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			map.put("message", "gagal menyimpan data");
			this.encodeToJson(response, map);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method =  RequestMethod.POST)
	public void updateBook(HttpServletRequest request, HttpServletResponse response, @RequestBody String data){
		//konversi json to book model
		ObjectMapper mapper = new ObjectMapper();
		try {
			Book book = mapper.readValue(data, Book.class);
			
			if(bookService.getBook(book.getId()) == null){
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void deleteBook(HttpServletRequest req, HttpServletResponse resp, @PathVariable("id") Long id){
		Book book = new Book();
		book.setId(id);		
		try {
			if(bookService.getBook(id) == null){
				map.put("message", "Data Buku tidak ditemukan");
				this.encodeToJson(resp, map);
			}else{
				//hapus book
				bookService.deleteBook(book);
				map.put("message", "Sukses menghapus data buku dengan id: "+book.getId());
				this.encodeToJson(resp, map);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("message", e.getMessage());
			this.encodeToJson(resp, map);
		}
		
	}
}
