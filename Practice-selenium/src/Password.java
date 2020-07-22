import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Password {

		WebDriver driver;
		
		WebDriverWait wait;
		
		public static void main(String[] args) {
			
			
			
			Password pass = new Password();
			pass.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
			
			WebDriverWait wait = new WebDriverWait(pass.driver, 5);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
			
		}
		
	}
	


