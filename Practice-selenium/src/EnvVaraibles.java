import java.awt.List;
import java.io.File;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EnvVaraibles {

	static WebDriver driver;
	
	static WebDriverWait wait;
	
	public static void main(String[] args) {
		
		
		By list = By.xpath("//div[@class=' css-26l3qy-menu']");
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		
		driver.get("https://staging.mystartupequity.com/login");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.name("email")).sendKeys("rajesh.ganessan+signeasy@letsventure.com");
		
		driver.findElement(By.name("password")).sendKeys("Admin@123");
		
		driver.findElement(By.xpath("//button[@class='btn btn-info btn-block mt-4 no_border_radius signin_button']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-info btn-block mt-4 no_border_radius signin_button']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().to("https://staging.mystartupequity.com/dashboard/vestingschedules/add");
		
		driver.findElement(By.xpath("//div[contains(@class,'css-1hwfws3')]")).click();
		
		wait = new WebDriverWait(driver, 15);
		
		boolean check = wait.until(ExpectedConditions.visibilityOfElementLocated(list)) != null;
		
		System.out.println(check);
		
		java.util.List<WebElement> cliffList = driver.findElements(By.xpath("//div[@class=' css-26l3qy-menu']//div[contains(@class,'option')]"));
		
		for (int i = 0; i < cliffList.size(); i++) {
			
			String text =  cliffList.get(i).getText();
			
			if(text.equalsIgnoreCase("Monthly")) {
				
				cliffList.get(i).click();
			}
			System.out.println(text);
		}
		
	}
	
	private static String getRandomFile() {
		
		File directory = new File(System.getProperty("user.dir") + "/Reports" );
		
		if(directory.exists()) {
			
			String[] files = directory.list();
			
			Random randomGenerator = new Random();
			int randomFile = randomGenerator.nextInt(files.length);
			String file = files[randomFile];
			
			return directory +"/" + file;
		} 
		return null;
	}
}
