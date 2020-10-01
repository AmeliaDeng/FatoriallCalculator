package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperator {

	public  static String getCell(String path, int rowNum, int columnNum) {
		
	    FileInputStream fileInputStream = null;
	    XSSFSheet xssfResultSheet = null;
	    String filePath = path;
	    
	    try {
			fileInputStream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("No input file ");
		}
	    XSSFWorkbook workbook = null;
	    try {
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			System.out.println("No sheet found ");
		}
	    xssfResultSheet = workbook.getSheet("Sheet1");

	        Row resultRow = xssfResultSheet.getRow(rowNum);
	        DataFormatter df = new DataFormatter();
	        String cellData = df.formatCellValue(resultRow.getCell(columnNum));
	        return cellData;
		
	}
}
