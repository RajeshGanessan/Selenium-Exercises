import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoSuggestiveDropdown {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void setup() {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		String URL = "https://jqueryui.com/autocomplete/";

		wait = new WebDriverWait(driver, 5);

		driver.get(URL);
	}

	@Test
	public void AutoSuggestive() {

		WebElement frame = driver.findElement(By.className("demo-frame"));

		driver.switchTo().frame(frame);

		WebElement autoSuggestTextBox = driver.findElement(By.xpath("//input[@id='tags']"));

		autoSuggestTextBox.sendKeys("r");

		selectOptionFromList("JavaScript");

	}

	//Selecting the option from the list
	private void selectOptionFromList(String string) {
		
		try {
			WebElement suggestiveList = driver.findElement(By.xpath("//ul[@id='ui-id-1']"));
			// waiting for element to be visible
			wait.until(ExpectedConditions.visibilityOf(suggestiveList));

			List<WebElement> SuggestiveOptions = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li"));

			String optionToSelect = "JavaScript";

			for (WebElement option : SuggestiveOptions) {

				if (option.getText().equals(optionToSelect)) {

					option.click();
					break;
				}
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
