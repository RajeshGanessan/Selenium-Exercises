import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class headLessTest {

	static WebDriver driver;
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("--headless");
		 
		 driver = new ChromeDriver(options);
		
		driver.get("https://staging.mystartupequity.com/login");
		
		String title = driver.getTitle();
		System.out.println(title);
		
	}
}
