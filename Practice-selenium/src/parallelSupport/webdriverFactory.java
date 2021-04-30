package parallelSupport;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class webdriverFactory {

    public static ThreadLocal<WebDriver> wdriver = new ThreadLocal<>();


    public static void setDriver(){

        WebDriverManager.chromedriver().setup();
        wdriver.set(new ChromeDriver());
    }

    public static synchronized WebDriver getDriver(){
        return wdriver.get();
    }

    public void closeBrowser(){
        wdriver.get().close();
        wdriver.remove();
    }
}
