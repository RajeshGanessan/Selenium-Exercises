import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DockerTest {

	
	
	@Test
    public void runTestOnDocker() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		
		
        // Hub Port at 4444
				WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		
        // Get URL
        driver.get("https://staging.mystartupequity.com/login");
        // Print Title
        System.out.println(driver.getTitle());
        
        driver.quit();
    }
}
