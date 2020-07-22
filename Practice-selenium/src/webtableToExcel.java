import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webtableToExcel {

	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		
		 driver = new ChromeDriver();
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
	}
	
	@Test
	public void WebTableHandle() {
		
		//getting only company list
		List<WebElement> companyList = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr/td[1]"));
		
		for(WebElement companyName : companyList) {
			
			String company = companyName.getText();
			System.out.println(company);
		}
		
		//
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr"));
		int rowsize = rows.size();
		System.out.println(rowsize);

		//getting specific column value based on condition
		for(int i=2;i<=rowsize;i++) {
			
			List<WebElement> columns = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr[" + i + "]/td"));
			
			for(WebElement columnval : columns) {
				
				String val = columnval.getText();
				String country = "Canada";
				if(val.equalsIgnoreCase(country)) {
					
					WebElement company = driver.findElement(By.xpath("//*[@id='customers']/tbody/tr[" + i + "]/td[1]"));
					String com = company.getText();
					System.out.println("The Company " + com + "is located in the " + country);
				}
//				System.out.println(val);
			}
		}
		
		//Getting all the date from the table
		for(int i=2;i<=rows.size();i++) {
			
			List<WebElement> columns = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr[" + i + "]/td"));

			for(int j=0;j<columns.size();j++) {
				
				System.out.print(columns.get(j).getText() +" ");
			}
			
			System.out.println();
		}
		
		
	}
}
