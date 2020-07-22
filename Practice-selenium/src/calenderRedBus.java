import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class calenderRedBus {

	static WebDriver driver;
	static WebDriverWait wait;
	
	int expMonth;
	int expYear;
	String expDate = null;
	
	String calMonth = null;
	int calYear ;


	List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec");

	@BeforeTest
	public void LaunchRedBus() {

		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);
		driver.get("https://www.redbus.in/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void searchBus() {

		wait = new WebDriverWait(driver, 10);
		JavascriptExecutor je = (JavascriptExecutor) driver;

		WebElement fromCity = driver.findElement(By.xpath("//input[@id='src']"));
		WebElement destCity = driver.findElement(By.xpath("//input[@id='dest']"));
		By cityListView = By.xpath("//ul[@class='autoFill']");
		String cityStop = "Gandhipuram";
		String destCityStop = "Indiranagar";

//		fromCity.clear();

		fromCity.sendKeys("coi");

		wait.until(ExpectedConditions.presenceOfElementLocated(cityListView));

		List<WebElement> CityList = driver.findElements(By.xpath("//ul[@class='autoFill']/li"));

		for (WebElement fromCityStop : CityList) {

			if (fromCityStop.getText().contains(cityStop)) {

				fromCityStop.click();
				break;
			}
		}
//		destCity.clear();

		destCity.sendKeys("Bang");

		wait.until(ExpectedConditions.presenceOfElementLocated(cityListView));

		// WebElement cityListView1 =
		// driver.findElement(By.xpath("//ul[@class='autoFill']"));

		List<WebElement> CityListto = driver.findElements(By.xpath("//ul[@class='autoFill']/li"));


		for (WebElement destStop : CityListto) {

			if (destStop.getText().contains(destCityStop)) {

				try {
					je.executeScript("arguments[0].scrollIntoView(false);", destStop);
					Thread.sleep(500);
					destStop.click();
					break;

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		}
	}
	
	@Test(priority = 2)
	public void SelectDateFromCal() throws InterruptedException {

		wait = new WebDriverWait(driver, 10);
		WebElement calender = driver.findElement(By.xpath("//label[contains(text(),'Onward Date')]"));
//		WebElement calCheck = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']"));
		
		calender.click();


		String departureDate = "28-11-2019";

		String[] dateSplit = departureDate.split("-");
		
		expDate = dateSplit[0]; //Date to be selected
		expMonth = Integer.parseInt(dateSplit[1]);
		expYear = Integer.parseInt(dateSplit[2]);
		
		boolean notFound = true;
		
		while(notFound) {
			
			String monthTitle = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']//td[@class='monthTitle']")).getText();
			String[] cal = monthTitle.split(" ");
			WebElement nextBtn = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']//button[contains(text(),'>')]"));
			WebElement prevBtn = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']//button[contains(text(),'<')]"));

			
			calMonth = cal[0];
			
			calYear = Integer.parseInt(cal[1]);
			
			
			if(months.indexOf(calMonth)+1 == expMonth && expYear == calYear) {
				
				selectDay(expDate);
				System.out.println("Date selected");
				notFound = false;
			}
			
			
			else if(months.indexOf(calMonth)+1 < expMonth && expYear == calYear) {
				
				nextBtn.click();
				System.out.println("clicked Next");
				Thread.sleep(2000);
			}
			
			else if(months.indexOf(calMonth) + 1 > expMonth && expYear == calYear) {
				
				prevBtn.click();
			}
		}
		
		driver.findElement(By.xpath("//button[@id='search_btn']")).click();
		

	}


	public void selectDay(String day) {

		// List of dates to be clicked
		List<WebElement> dates = driver
				.findElements(By.xpath("//div[contains(@id,'onward_cal')]//tbody//td[contains(@class,'day')]"));

		for (WebElement date : dates) {

			if (date.getText().equalsIgnoreCase(day)) {

				date.click();
				break;
			}
		}
	}


}
