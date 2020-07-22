import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class windowHandle {

	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeTest
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver = new ChromeDriver(ops);
		
		
		driver.get("https://www.toolsqa.com/automation-practice-switch-windows/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		driver.manage().deleteAllCookies();
		
	}
	
	@Test(priority=1,description = "handling window",enabled = false)
	public void windowHandle() throws InterruptedException {
		
		wait = new WebDriverWait(driver, 15);
		WebElement popUp = driver.findElement(By.id("button1"));
		
		wait.until(ExpectedConditions.elementToBeClickable(popUp));
			
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		for(int i=1;i<=3;i++) {
		js.executeScript("arguments[0].click();", popUp);
			Thread.sleep(2000);
		}
		String parWindow = driver.getWindowHandle();
		
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String window : allWindows) {
			
			
			System.out.println(window);
			
			if(!parWindow.equals(window)) {
				
				driver.switchTo().window(window);
				
				driver.navigate().to("https://www.youtube.com/");
				String title = driver.getTitle();
				System.out.println("Title of child window" +title);
				
			}
			
			driver.switchTo().window(parWindow);
			String title = driver.getTitle();
			System.out.println("Parent Window Title"+ title);
			
			
		}
	}
	
	@Test(description = "Handling windows method 2")
	public void getWindow() throws InterruptedException {

		wait = new WebDriverWait(driver, 5);
		WebElement popUp = driver.findElement(By.id("button1"));
		
		wait.until(ExpectedConditions.elementToBeClickable(popUp));
			
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		for(int i=1;i<=3;i++) {
		js.executeScript("arguments[0].click();", popUp);
			Thread.sleep(2000);
		}
		
		String parWindow = driver.getWindowHandle();
		
		Set<String> allWindows = driver.getWindowHandles();
					
		Iterator<String> it = allWindows.iterator();
		
		String child = it.next();
		 
		}
	}


