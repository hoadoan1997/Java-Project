import java.io.IOException;
import java.util.List;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	private static int number = 1;
	//Hàm ghi file Excel từ dữ liệu thuộc List<ThuaDat>
	public void writeExcel(List<ThuaDat> thuaDat, String excelFilePath) throws IOException {
		Workbook wb = getWorkbook(excelFilePath);
		Sheet sheet = (Sheet) wb.createSheet();
		createHeaderRow(sheet);
		int rowCount = 0;
		for(ThuaDat td:thuaDat) {
			Row row = sheet.createRow(++rowCount);
			writeBook(td, row);
		}
		try(FileOutputStream outputStream = new FileOutputStream(excelFilePath)){
			wb.write(outputStream);
		}
	}
	//Hàm kiểm tra đuôi của file được ghi là xlsx hoặc xls
	private Workbook getWorkbook(String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook();
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook();
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	//Hàm tạo Header trong file Excel
	private void createHeaderRow(Sheet sheet) {
		 
	    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    Font font = sheet.getWorkbook().createFont();
	    font.setBold(true);
	    font.setFontHeightInPoints((short) 16);
	    cellStyle.setFont(font);
	 
	    Row row = sheet.createRow(0);
	    
	    Cell cellSTT = row.createCell(0);
	    cellSTT.setCellStyle(cellStyle);
	    cellSTT.setCellValue("STT");
	 
	    Cell cellDiaChi = row.createCell(1);
	    cellDiaChi.setCellStyle(cellStyle);
	    cellDiaChi.setCellValue("Địa chỉ");
	 
	    Cell cellChuSoHuu = row.createCell(2);
	    cellChuSoHuu.setCellStyle(cellStyle);
	    cellChuSoHuu.setCellValue("Chủ sở hữu");
	    
	    Cell cellDienTich = row.createCell(3);
	    cellDienTich.setCellStyle(cellStyle);
	    cellDienTich.setCellValue("Diện tích");
	    
	    Cell cellLoaiNha = row.createCell(4);
	    cellLoaiNha.setCellStyle(cellStyle);
	    cellLoaiNha.setCellValue("Loại nhà");
	    
	    Cell cellMucDich = row.createCell(5);
	    cellMucDich.setCellStyle(cellStyle);
	    cellMucDich.setCellValue("Mục đích");
	    
	    Cell cellGiaTien = row.createCell(6);
	    cellGiaTien.setCellStyle(cellStyle);
	    cellGiaTien.setCellValue("Giá tiền");
	}
	//Hàm ghi dữ liệu từ ThuaDat ra từng dòng trong file Excel
	private void writeBook(ThuaDat thuaDat, Row row) {
	    Cell cell = row.createCell(0);
	    cell.setCellValue(number++);
	 
	    cell = row.createCell(1);
	    cell.setCellValue(thuaDat.getDiaChi());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(thuaDat.getChuSoHuu());
	    
	    cell = row.createCell(3);
	    cell.setCellValue(thuaDat.getDienTich());
	    
	    cell = row.createCell(4);
	    cell.setCellValue(thuaDat.getLoaiNha());
	    
	    cell = row.createCell(5);
	    cell.setCellValue(thuaDat.getMucDich());
	    
	    cell = row.createCell(6);
	    cell.setCellValue(thuaDat.getGiaTien());
	}

}
