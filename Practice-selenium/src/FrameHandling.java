import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameHandling {

	
	
	private static WebDriver driver;
	
	
	@BeforeTest
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		driver = new ChromeDriver(ops);
		
		
		driver.get("http://www.globalsqa.com/demo-site/frames-and-windows/#iFrame");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		driver.manage().deleteAllCookies();
	}
	
	@Test()
	public void frameHandling() {
		
		driver.findElement(By.xpath("//li[@id='iFrame']"));
		
		System.out.println("The Number of the frames in the page " + getNoOfFrames());
		
		
		
	}
	
	public static int getNoOfFrames() {
		
		
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		
		for(int i=0;i<=frames.size();i++) {
			
			String frameName = frames.get(0).getAttribute("name");
			
			System.out.println("Frame " + i + " = " + frameName);
		}
		
		int numberOfFrames = frames.size();
		
		return numberOfFrames;
	}
	
	public static void switchToFrame(int index) {
		
		
		try {
			driver.switchTo().frame(index);
			System.out.println("Switched to the frame with index" +index);
		}
		catch (NoSuchFrameException e) {
			// TODO: handle exception
			System.out.println("No such frame was found");
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("unable to navigate to such frame");
		}
	}
}
 