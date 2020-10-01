package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelProcesser {
	public  static XSSFSheet getSheet(String filePath, int index) {
		XSSFSheet sheet = null;
		FileInputStream file;
		try {
			file = new FileInputStream(new File(filePath));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
		
			// Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(index);
			
			// close Workbook
			workbook.close();
			// close File
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sheet;
	}
	
	// This method will read every data from the excel spreadsheet
	public static String[][] getExcelData(XSSFSheet sheet) {

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		String[][] excelData = new String[rowCount][colCount];
		String cellValue;

//		System.out.println("There have " + rowCount + " rows and " + colCount + " columns in the sheet.");

		java.util.Iterator<Row> rowIterator = sheet.rowIterator();
    	int x = 0;

		while (rowIterator.hasNext()) {
			x++;
			Row row = rowIterator.next();
			// For the row, iterate through columns
			Iterator<Cell> cellIterator = row.cellIterator();
			int y = 0;
			while (cellIterator.hasNext()) 
			{				
				y++;
				Cell cell = cellIterator.next();

				if (CellType.STRING == cell.getCellType()) {

					cellValue = cell.getStringCellValue();
					excelData[x-1][y-1]=cellValue;
//					System.out.println(excelData[x-1][y-1]);
				
				} else if (CellType.NUMERIC == cell.getCellType()) {
					cellValue = String.valueOf(cell.getNumericCellValue());
					excelData[x-1][y-1]=cellValue;
//					System.out.println(excelData[x-1][y-1]);
				}

			}
		}

		return excelData;
	}
	
	//This method is to read the test data from the Excel cell
	public static String getCellData(XSSFSheet sheet, int row, int col){
		String result = "";
		try{
			String[][] excel = ExcelProcesser.getExcelData(sheet);
			result = excel[row - 1][col - 1];
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Exception happen in the row "+ row + " column "+ col);
			System.out.println(e);
		}
		return result;
	}
	
	
	public static void writeXlsxFile(String Path,String SheetName,String result, int row, int col ) throws Exception {
		
		 try {
//			final String Path_TestData = "C://AProject//Work//A-CCR QA//AutomationTestExcel//TestData.xlsx";
//	      final String File_TestData = "Operator-Ame";
			
			FileInputStream file = new FileInputStream(Path);
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheet(SheetName);
			
			Row sheetrow = sheet.getRow(row-1);
			if(sheetrow == null){
			    sheetrow = sheet.createRow(row-1);
			}
			//Update the value of cell
			Cell cell = sheetrow.getCell(col-1);
			if(cell == null){
				cell = sheetrow.createCell(col-1);
			}
			
			cell.setCellValue(result);
			
			try {
				FileOutputStream fileOut = new java.io.FileOutputStream(new File(Path));
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch ( IOException e) {
				e.printStackTrace();
			}
	        
	        
	} catch (FileNotFoundException e) {
	    e.printStackTrace();        
	    }
		 
		 catch (IOException e) {
	         e.printStackTrace();
	     }

	}

}
