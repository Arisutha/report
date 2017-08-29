/**
 * 
 */
package com.sutha.id.web;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author sutaeni
 *
 */
public class Report {
	private Log log = LogFactory.getLog(getClass());

	@SuppressWarnings("unchecked")
	protected void exportToPDF(HttpServletRequest request, HttpServletResponse response, DataSource dataSource) {
		String reportFile = request.getParameter("reportFile");

		if (reportFile == null)
			reportFile = (String) request.getAttribute("reportFile");

		String outputFileName = request.getParameter("outputFile");
		if (outputFileName == null)
			outputFileName = (String) request.getAttribute("outputFileName");

		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/report");

		Connection con = null;
		try {
			ServletOutputStream outputStream = response.getOutputStream();

			Map<String, Object> params = null;
			try {
				params = (Map<String, Object>) request.getAttribute("params");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			if (params == null)
				params = new HashMap<String, Object>();

			response.setContentType("application/pdf");
			if ((outputFileName != null) && !outputFileName.isEmpty())
				response.setHeader("Content-disposition", "attachment;filename=" + outputFileName + ".pdf");

			con = dataSource.getConnection();

			log.debug("Compiling " + reportFile + ".jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(realPath + "/" + reportFile + ".jrxml");

			log.debug("Filling report...");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

			log.debug("Exporting report...");
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			log.debug("Report exported.");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}
}
