package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Table {

    WebDriver driver;
    WebDriverWait wait;

    private static final String EMAIL = "rajesh.ganessan+frontexp@letsventure.com";
    private static final String PASSWORD = "Admin@123";


    @BeforeTest
    public void setup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://staging.mystartupequity.com/login");
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void TableCheck(){
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(EMAIL);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//button[@class='btn btn-info btn-block mt-4 no_border_radius signin_button']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='ESOP Management']")));
        driver.findElement(By.xpath("//button[text()='ESOP Management']")).click();

        driver.navigate().to("https://staging.mystartupequity.com/esop/vestingtable");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr")));
        List<WebElement>  rows = driver.findElements(By.xpath("//tbody//tr"));

     var count =    rows.stream()
                .skip(1)
                .map(element -> element.findElements(By.tagName("td")))
                .map(webElements -> webElements.get(8))
                .filter(element -> element.getText().equals("Accepted"))
                .count();

        System.out.println(count);

    }
}
