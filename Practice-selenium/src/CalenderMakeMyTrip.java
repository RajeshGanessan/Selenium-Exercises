import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderMakeMyTrip {

	static WebDriver driver;
	static WebDriverWait wait;
	
	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		
		driver.get("https://www.makemytrip.com");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		ChooseDate();
		
		
	}
	
	
	//choosing Date
	
	public static void ChooseDate() throws InterruptedException {
		
		
		
		wait = new WebDriverWait(driver, 5);
		WebElement calender = driver.findElement(By.xpath("//label[@for='departure']"));
		
		calender.click();
		
		List<WebElement> Dates = driver.findElements(By.xpath("//div[@class='DayPicker-Week']/div"));
		System.out.println(Dates.size());
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DayPicker-Caption']/div")));
		
		//Selecting Departure Date
		for(WebElement departureDate : Dates) {
			
			if(departureDate.getAttribute("class").contains("--today")) {
				
				departureDate.click();
				System.out.println("Clicked Date ");
				break;
			}
		}
		
		Thread.sleep(2000);
		//Selecting Return  date
		int noOfDays = 7;
		System.out.println("Total days of Journey = " + noOfDays);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		cal.add(Calendar.DATE, noOfDays);
		String Todate = dateFormat.format(cal.getTime());
//		WebElement ReturnCal = driver.findElement(By.xpath("//div[contains(@class,'fsw_inputBox')]//label[@for='return']"));

		driver.findElement(By.xpath("//div[contains(@class,'reDates')]/div/label")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DayPicker-Caption']/div")));

		String ToDate = "//div[@class='DayPicker-Day' and contains(@aria-label,'" + Todate + "')]";
		driver.findElement(By.xpath(ToDate)).click();
		System.out.println("Return day Clicked as " + Todate);
	}
}
