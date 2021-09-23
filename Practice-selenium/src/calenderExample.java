import org.openqa.selenium.WebDriver;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class calenderExample {

	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) throws InterruptedException, ParseException {

		
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driver = new ChromeDriver();
		driver.get("");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.manage().deleteAllCookies();
		
		// Login
		driver.findElement(By.name("login")).sendKeys("10000005");
		driver.findElement(By.name("password")).sendKeys("admin@123");
		driver.findElement(By.id("sign_in_button")).click();
		JSwait();
		Thread.sleep(10000);
		Scroll("400");
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		JSwait();
		Thread.sleep(3000);
		Select dp = new Select(driver.findElement(By.xpath("//select[@id='leave_type']")));
		dp.selectByIndex(1);
		JSwait();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[contains(text(),'Full Session')]")).click();
		Thread.sleep(1000);

		removeReadOnly(driver.findElements(By.xpath("//input[@id='start_leave_date']")));
		removeReadOnly(driver.findElements(By.xpath("//input[@id='end_leave_date']")));
		String startDate = SendDesiredDate(driver.findElement(By.name("from_date")), 4);
		System.out.println(startDate);
		String endDate = SendDesiredDate(driver.findElement(By.name("to_date")), 9);
		System.out.println(endDate);
		Actions press = new Actions(driver);
		press.sendKeys(Keys.ENTER).build().perform();
		
		press.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(3000);
		String date1 = startDate;
		String date2 = endDate;

		// Reason
		driver.findElement(By.xpath("//input[@placeholder='Enter the reason for leave']")).sendKeys("Sick");
		date1.replace("-", "/");
		date2.replace("-", "/");
		
		String format = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateObj1 = sdf.parse(date1);
		Date dateObj2 = sdf.parse(date2);
		System.out.println(dateObj1);
		System.out.println(dateObj2 + "\n");
		long diff = dateObj2.getTime() - dateObj1.getTime();
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println("difference between days: " + diffDays);

		if (diffDays >= 3) {
			// File Name
			driver.findElement(By.xpath("(//input[@class='form-control'])[3]")).sendKeys("Sick Document");
			// upload
			File file = new File("./UploadDocx/1528350543.docx");
			String path = file.getAbsolutePath();
			driver.findElement(By.xpath(
					"//div[@id='candidate_file_upload_box_upload_3']//label[@class='file_upload_input']//input[@name='file_upload[]']"))
					.sendKeys(path);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='btn_apply_leave']")).click();
		}
	}

	public static ExpectedCondition<Boolean> JSwait() {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver)
						.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
	}

	public static void Scroll(String value) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + value + ")");
	}

	public static void removeReadOnly(List<WebElement> element) {
		List<WebElement> inputs = element;
		for (WebElement input : inputs) {
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", input);
		}
	}

	public static String SendDesiredDate(WebElement element, int val) {
		SimpleDateFormat currentdate = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, val);
		String newDate = currentdate.format(c.getTime());
		System.out.println("Date after Addition: " + newDate);
		element.sendKeys(newDate); 
		return newDate;
	}
}
