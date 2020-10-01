package testCases;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import util.ExcelOperator;
import util.ExcelProcesser;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		String filePath="I:\\Project\\FatoriallCaculator\\FatoriallCalculator\\factorialCalculator\\src\\test\\java\\testData\\testData.xlsx";
	
		  int row=1;

		  for (int i=1; i<=3;i++) {
			  
		  String testInput= ExcelOperator.getCell(filePath, row, 0);		  
		  String expectedResult=ExcelOperator.getCell(filePath, row, 1);

		  System.out.println(testInput);
		  System.out.println(expectedResult);  
		  row++;
		  }
	}

}
