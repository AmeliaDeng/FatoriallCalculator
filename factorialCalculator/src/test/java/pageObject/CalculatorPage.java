package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {

	
	private WebDriver driver1;
	
	public CalculatorPage(WebDriver driver) {
		this.driver1 = driver;
	}
	
	
	@FindBy(how=How.ID,using="number")
	private WebElement inputArea;
	

	@FindBy(how=How.ID,using="getFactorial")
	private WebElement calButton;
	
	@FindBy(how=How.ID,using="resultDiv")
	private WebElement result;
	
	public void setInput(String input) {
		
		driver1.navigate().refresh();
		inputArea.clear();
		inputArea.sendKeys(input);
		
	}
	
	public void clickCalButton() {
		
		calButton.click();
	}
	
	public String getResult() {
		
		return result.getText();
	}
	
}
