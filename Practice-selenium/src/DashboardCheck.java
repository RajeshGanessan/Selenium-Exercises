import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.*;

public class DashboardCheck<TotalPoolEle> {

        protected static WebDriver driver;

        public static void main(String[] args) {
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


//            driver.navigate().to("https://staging.mystartupequity.com/dashboard/");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//            List<String> overviewText = Arrays.asList("Total Pool Size" ,"Options Granted","Options left in Pool","Total Options Vested");
//
//            Map<String, Long> DashboardStats =  new HashMap<>();
//
//            for (String overview : overviewText){
//
//                DashboardStats.put(overview,Long.parseLong(driver.findElement(By.xpath("//div[@class='overview-card']//div[contains(text(),'" + overview + "')]/following-sibling::div"))
//                        .getAttribute("innerText").split(" ")[0]));
//                   }
//
//            for(Map.Entry<String,Long> entry : DashboardStats.entrySet()){
//                System.out.println("Key = " + entry.getKey() +
//                        ", Value = " + entry.getValue());
//            }

            Map<String,String> DashboardValues = getDashboardStats();

          Long totalSize = Long.parseLong(DashboardValues.get("Total Pool Size"));
          Long OptionsGranted = Long.parseLong(DashboardValues.get("Options Granted"));
          Long OptionsLeft = Long.parseLong(DashboardValues.get("Options left in Pool"));
          Long OptionsVested = Long.parseLong(DashboardValues.get("Total Options Vested"));
            System.out.println(totalSize);
            System.out.println(OptionsGranted);
            System.out.println(OptionsLeft);
            System.out.println(OptionsVested);

            Long actual = OptionsLeft+OptionsGranted;
            Assert.assertEquals(totalSize,actual);
            System.out.println("Both are equal, success" + actual + " " + totalSize);
        }


    public static Map<String,String> getDashboardStats(){

        List<String> overviewText = Arrays.asList("Total Pool Size" ,"Options Granted","Options left in Pool","Total Options Vested");

        Map<String, String> DashboardStats =  new HashMap<>();

            for(String overviewOptions : overviewText) {
                DashboardStats.put(overviewOptions,
                        (driver.findElement(By.xpath("//div[@class='overview-card']//div[contains(text(),'" + overviewOptions + "')]" +
                        "/following-sibling::div"))
                        .getAttribute("innerText").split(" ")[0]));
            }
            return DashboardStats;
        } 
    }






