package com.sutha.id.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.sutha.id.model.Book;

public class ExcelBookReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get data
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) map.get("listBooks");
		Map<String, CellStyle> styles = createStyles(workbook);
		//create sheet
		Sheet sheet = workbook.createSheet("list books");
		Row row = null;
		Cell cell = null;
		int r = 2;
		int c = 0;
		
		//style for header cell
		CellStyle style = workbook.createCellStyle();
		style = createBorderedStyle(workbook);
		style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		//create header cell
		Row title = sheet.createRow(0);
		title.setHeightInPoints(35);
		for(int i=0; i<=5; i++){
			title.createCell(i).setCellStyle(styles.get("title"));
		}
			
		Cell titleCell = title.getCell(2);
		titleCell.setCellValue("Book Data");
		row = sheet.createRow(r++);
		
		cell = row.createCell(c++);
		cell.setCellStyle(style);
		cell.setCellValue("Id");
		
		cell = row.createCell(c++);
		cell.setCellStyle(style);
		cell.setCellValue("Book Name");
		
		cell = row.createCell(c++);
		cell.setCellStyle(style);
		cell.setCellValue("Author");
		
		cell = row.createCell(c++);
		cell.setCellStyle(style);
		cell.setCellValue("Release");
		
		cell = row.createCell(c++);
		cell.setCellStyle(style);
		cell.setCellValue("Price");
		
		cell = row.createCell(c++);
		cell.setCellStyle(style);
		cell.setCellValue("Stock");
		
		//create data cell
		CellStyle rowStyle = createBorderedStyle(workbook);
		for(Book book : books){
			row = sheet.createRow(r++);			
			for(int cellnum = 0; cellnum<=5; cellnum++){
				Cell cell2 = row.createCell(cellnum);
				cell2.setCellStyle(rowStyle);
				
				if(cellnum == 0){
					cell2.setCellValue(book.getId());
				}else if(cellnum == 1){
					cell2.setCellValue(book.getName());
				}else if(cellnum == 2){
					cell2.setCellValue(book.getAuthor());
				}else if(cellnum == 3){
					cell2.setCellValue(book.getReleaseDate().toString());
				}else if(cellnum == 4){
					cell2.setCellValue(book.getPrice());
				}else if(cellnum == 5){
					cell2.setCellValue(book.getStock());
				}				
				
			}
		}
		
		for(int i=0;i<6; i++){
			sheet.autoSizeColumn(i, true);
		}
	}

	//style for border
	protected CellStyle createBorderedStyle(Workbook workbook){
		CellStyle style = workbook.createCellStyle();
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}
	
	protected Map<String, CellStyle> createStyles(Workbook wb){
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)14);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleFont.setItalic(true);
        titleFont.setFontName("Trebuchet MS");
        style = wb.createCellStyle();
        style.setFont(titleFont);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderBottom(CellStyle.BORDER_DOTTED);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        styles.put("title", style);
		return styles;
	}
}