package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;
import pageObject.CalculatorPage;
import util.BrowserHelper;
import util.ExcelOperator;

public class CalFactoriall {

	WebDriver driver;
	CalculatorPage calPg; 
	
	private String localDir = System.getProperty("user.dir");
	private String path = "\\src\\test\\java\\testData\\testData.xlsx";
	private String filePath =localDir+path;
	
    @BeforeMethod
    public void beforeClass() {
    	driver = BrowserHelper.getDriver("chrome", "https://qainterview.pythonanywhere.com/");
		calPg = PageFactory.initElements(driver, CalculatorPage.class);
    }
 
    @AfterMethod
    public void afterClass() {
        driver.close();
    }
    
   /*Test case1: get test data and expected result from excel, then calculate result. At the end, compare expected result and actual result */
	@Test
	public void calResult() throws InterruptedException {
		

		SoftAssert softAssertion = new SoftAssert();
		
		// loop excel to get test data
		int row = 1;

		for (int i = 1; i < 6; i++) {

			String testInput = ExcelOperator.getCell(filePath, row, 0);
			String expectedResult = ExcelOperator.getCell(filePath, row, 1);

			System.out.println("input: "+ testInput);
			calPg.setInput(testInput);
			calPg.clickCalButton();

			Thread.sleep(1000);
			// get the actual result
			String actualResult = calPg.getResult();

			System.out.println("actual Result:" + actualResult);

			System.out.println("expected Result:" + expectedResult);
			// compare result with expected result
			softAssertion.assertEquals(expectedResult, actualResult);
			row++;

		}
		softAssertion.assertAll();

	}
	/*Test case2: Calculate result without any input  */
	@Test
	public void calResultWithoutInput() throws InterruptedException {
		
		calPg.clickCalButton();
		
		Thread.sleep(1000);
		// get the actual result
		String actualResult = calPg.getResult();

		System.out.println("actual Result" + actualResult);
		
		String expectedResult = "Please enter an integer";
		Assert.assertEquals(expectedResult, actualResult);

	}

}
