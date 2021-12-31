package person.database.test;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private String reportName;
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private XSSFSheet sheet = null;
	private int row = 1;
	private int cell = 0;
	private int columns = 0;
	
	public ExcelUtils(String reportName) {
		
		this.reportName = reportName;
		
		sheet = createSheet(reportName);
	}
	
	public XSSFSheet createSheet(String report){
		
		XSSFSheet sheet = workbook.createSheet(report);
		
		sheet.setZoom(115);
		
		return sheet;
	}
	
	public XSSFRow getRow(int index){
		
		XSSFRow row = sheet.getRow(index);
		
		if(row == null){
			
			row = createRow(sheet, index);
		}
		
		return row;
	}
	
	public XSSFRow createRow(XSSFSheet sheet, int index){
		
		return sheet.createRow(index);
	}
	
	public XSSFCell getCell(XSSFRow row, int index){
		
		XSSFCell cell = row.getCell(index);
		
		if(cell == null){
			
			cell = createCell(row, index);
		}
		
		return cell;
	}
	
	public XSSFCell createCell(XSSFRow row, int index){
		
		return row.createCell(index);
	}
	
	public XSSFCellStyle getHeaderStyle(){
		
		XSSFFont headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 11);
		headerFont.setFontName("Trebuchet MS");
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		headerStyle.setLocked(true);
		headerStyle.setWrapText(true);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		
		return headerStyle;
	}
	
	public XSSFCellStyle getCellStyle(){
		
		XSSFFont recordFont = workbook.createFont();
		recordFont.setFontHeightInPoints((short) 10);
		recordFont.setFontName("Trebuchet MS");
		
		XSSFCellStyle recordStyle = workbook.createCellStyle();
		recordStyle.setFont(recordFont);
		recordStyle.setWrapText(true);
		recordStyle.setAlignment(HorizontalAlignment.CENTER);
		recordStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		recordStyle.setBorderRight(BorderStyle.THIN);
		recordStyle.setBorderBottom(BorderStyle.THIN);
		recordStyle.setBorderLeft(BorderStyle.THIN);
		recordStyle.setBorderTop(BorderStyle.THIN);
		
		return recordStyle;
	}
	
	public XSSFCellStyle getSuccessCellStyle(){
		
		XSSFFont recordFont = workbook.createFont();
		recordFont.setFontHeightInPoints((short) 10);
		recordFont.setFontName("Trebuchet MS");
		recordFont.setColor(IndexedColors.WHITE.getIndex());
		
		XSSFCellStyle recordStyle = workbook.createCellStyle();
		recordStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		recordStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		recordStyle.setFont(recordFont);
		recordStyle.setWrapText(true);
		recordStyle.setAlignment(HorizontalAlignment.CENTER);
		recordStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		recordStyle.setBorderRight(BorderStyle.THIN);
		recordStyle.setBorderBottom(BorderStyle.THIN);
		recordStyle.setBorderLeft(BorderStyle.THIN);
		recordStyle.setBorderTop(BorderStyle.THIN);
		
		return recordStyle;
	}
	
	public XSSFCellStyle getFailCellStyle(){
		
		XSSFFont recordFont = workbook.createFont();
		recordFont.setFontHeightInPoints((short) 10);
		recordFont.setFontName("Trebuchet MS");
		recordFont.setColor(IndexedColors.WHITE.getIndex());
		
		XSSFCellStyle recordStyle = workbook.createCellStyle();
		recordStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		recordStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		recordStyle.setFont(recordFont);
		recordStyle.setWrapText(true);
		recordStyle.setAlignment(HorizontalAlignment.CENTER);
		recordStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		recordStyle.setBorderRight(BorderStyle.THIN);
		recordStyle.setBorderBottom(BorderStyle.THIN);
		recordStyle.setBorderLeft(BorderStyle.THIN);
		recordStyle.setBorderTop(BorderStyle.THIN);
		
		return recordStyle;
	}
	
	public void addHeaderValue(String value){
		
		XSSFRow row = getRow(0);
		XSSFCell cell = getCell(row, columns++);
		cell.setCellStyle(getHeaderStyle());
		cell.setCellValue(value);
	}
	
	public void addRow(){
		
		if(cell == columns){
			row++;
			cell = 0;
		}
	}
	
	public void addRecordSequance(){
		
		addRow();
		
		createCell(row, cell++, row);
	}
	
	public void addRecordValue(String value){
		
		System.out.println(value);
		
		addRow();
		
		createCell(row, cell++, value);
	}
	
	public void createCell(int rowNo, int cellNo, String value){
		
		XSSFRow row = getRow(rowNo);
		XSSFCell cell = getCell(row, cellNo);
		
		if("Yes".equals(value)){
			
			cell.setCellStyle(getSuccessCellStyle());
			
		}else if("No".equals(value)){
			
			cell.setCellStyle(getFailCellStyle());
			
		}else{
			
			cell.setCellStyle(getCellStyle());
		}
		
		cell.setCellValue(value);
	}
	
	public void createCell(int rowNo, int cellNo, double value){
		
		XSSFRow row = getRow(rowNo);
		XSSFCell cell = getCell(row, cellNo);
		cell.setCellStyle(getCellStyle());
		cell.setCellValue(value);
	}
	
	public byte[] getWorkbookStream(){
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		for(int i=0; i<sheet.getRow(0).getLastCellNum(); i++){
			
			sheet.autoSizeColumn(i); 
		}

		try {
			
			workbook.write(stream);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return stream.toByteArray();
	}
	
	public void writeWorkbook() throws Exception{
		
		for(int i=0; i<=sheet.getLastRowNum(); i++) {
			
			XSSFRow row = sheet.getRow(i);
			
			for(int j=1; j<row.getLastCellNum(); j++) {
				
				System.out.print(row.getCell(j) + "\t");
			}
			
			System.out.println();
		}
		
		Files.write(Paths.get(reportName + ".xlsx"), getWorkbookStream());
	}
}
