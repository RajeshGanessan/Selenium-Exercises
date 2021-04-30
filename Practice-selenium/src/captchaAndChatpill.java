
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class captchaAndChatpill {

    static WebDriver driver;
    static WebDriverWait wait;
   static JavascriptExecutor js;

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         js = (JavascriptExecutor) driver;

        driver.get("https://staging.mystartupequity.com/login");
        driver.manage().window().maximize();

        driver.findElement(By.name("email")).sendKeys("rajesh.ganessan+dec30@letsventure.com");
        driver.findElement(By.name("password")).sendKeys("Admin@123");

        driver.findElement(By.xpath("//div[contains(@class,'jss2')]//button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='ESOP Management']")));
        driver.findElement(By.xpath("//button[text()='ESOP Management']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Vesting Table']")));
        System.out.println(driver.findElement(By.xpath("//a[text()='Vesting Table']")).isEnabled());
        js.executeScript("return document.getElementsByClassName('grecaptcha-badge')[0].remove();");
        driver.findElement(By.xpath("//a[text()='Vesting Table']")).click();

//

    }

    private static void ChatPillHidding(){

        WebElement chatPill = driver.findElement(By.cssSelector("div[id='fc_frame']"));
        if(chatPill.isDisplayed()){
            System.out.println("chat pill displayed");
            js.executeScript("return document.getElementsByClassName('fc-widget-normal')[0].remove();");
            js.executeScript("return document.getElementById('fc_push_frame').remove();");
        }
//      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='fc_widget']")))
//      Thread.sleep(2000);

//        WebElement chatFrame = driver.findElement(By.xpath("//iframe[@id='fc_widget']"));
//
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(chatFrame));
//        driver.findElement(By.xpath("//div[@class='chat-content']")).click();
//        Thread.sleep(2000);
//        WebElement closeIcon = driver.findElement(By.xpath("//div[@id='ember4']/child::div[1]"));
//        wait.until(ExpectedConditions.visibilityOf(closeIcon));
//        js.executeScript("arguments[0].click();", closeIcon);

//        driver.switchTo().defaultContent();

//                System.out.println("execution started");
////        js.executeScript("arguments[0].setAttribute('style','display:none')",chatFrame);
////        js.executeScript("arguments[0].setAttribute('style','z-index:-100')",chatFrame);
//        System.out.println("Script execution Completed - chat box Hidden");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'sidebar-menu')]/a[text()='Reports']")));
        driver.findElement(By.xpath("//a[text()='Reports']")).click();

    }

    private static void RecaptchaCheck(){

//        WebElement captchaBadge = driver.findElement(By.className(""))
    }
}