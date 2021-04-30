package SeleniumChallenges;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Challenge1 {

   public static WebDriver driver;
    public static void main(String[] args) {

        System.out.println("Naveen Challenge 1 ");
        WebDriverManager.chromedriver().setup();
        String worldPopulationXpath = "//div[@class='maincounter-number']/span[@class='rts-counter']";
        String today_andThisyear  = "//div[text()='Today' or text()='This year']//parent::div//span[@class='rts-counter']";
        
         driver = new ChromeDriver();

        driver.get("https://www.worldometers.info/world-population/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        int count=1;
        while(count<=20){
            if(count == 21) break;

            System.out.println("<----World population---->");
            printPopulation(worldPopulationXpath);
            System.out.println("<------- Today and This Year count --------->");
            printPopulation(today_andThisyear);
            System.out.println("====================================");
            Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
            count++;
        }

        driver.close();
    }

    private static void printPopulation(String locator){

//        List<WebElement> items = driver.findElements(By.xpath(locator));
//
//        for (WebElement e : items) {
//            System.out.println(e.getText());
//        }

        driver.findElements(By.xpath(locator)).forEach(s -> System.out.println(s.getText()));
    }
}
