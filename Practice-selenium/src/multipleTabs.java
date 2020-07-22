import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class multipleTabs {

	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) throws InterruptedException {


		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://staging.mystartupequity.com/login");

		driver.manage().window().maximize();

		driver.findElement(By.name("email")).sendKeys("rajesh.ganessan+hello0@letsventure.com");

		driver.findElement(By.name("password")).sendKeys("Admin@123");

		driver.findElement(By.xpath("//button[@class='btn btn-info btn-block mt-4 no_border_radius signin_button']"))
				.click();
		driver.findElement(By.xpath("//button[@class='btn btn-info btn-block mt-4 no_border_radius signin_button']"))
				.click();


		driver.navigate().to("https://staging.mystartupequity.com/dashboard/");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement approveBtn = driver
				.findElement(By.xpath("//button[contains(@class,'esop-button') and contains(text(),'Approve')]"));

		String CurrentWindow = driver.getWindowHandle();

		wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.elementToBeClickable(approveBtn));

		approveBtn.click();

		Set<String> allWindows = driver.getWindowHandles();

		for (String actual : allWindows) {

			if (!actual.equalsIgnoreCase(CurrentWindow)) {

				driver.switchTo().window(actual);
				checkPageIsReady();

				wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Finish')]")));
				String url = driver.getCurrentUrl();
				String title = driver.getTitle();
				System.out.println(url + " Title of the page" + title);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//div[@id='auto_docLoader']//div[@class='download-progress-dialog-content']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Finish')]")));

				driver.switchTo().frame((WebElement) driver.findElement(By.xpath("//div[contains(@class,'mobile-view-column')]//iframe")));

				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='marker_0']")));
				driver.findElement(By.xpath("//img[@class='marker_0']")).click();

				driver.switchTo().defaultContent();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='sign-modal']/h4")));
				if (driver.findElement(By.xpath("//div[@id='sign-modal']/h4")).isDisplayed()) {
					driver.findElement(By.xpath("//td[@id='initial0']")).click();
					
					driver.findElement(By.xpath("//button[@class='ng-binding tiny radius']")).click();
					driver.findElement(By.xpath("//button[contains(text(),'Finish')]")).click();
					
					driver.switchTo().window(CurrentWindow);
					
					System.out.println(driver.getTitle());
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert' and contains(text(),'Approved Successfully')]")));
					
					WebElement successMsg = driver.findElement(By.xpath("//div[@role='alert' and contains(text(),'Approved Successfully')]"));
					
						if(successMsg.isDisplayed()) {
							
							System.out.println(" SignEasy Passed");
						}
				}
			}
		}

	}

	// To check whether the page is ready
	public static void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}
}
