import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webtableHandle {

		static  WebDriver driver = new ChromeDriver();

	public static void main(String[] args) throws ParseException {
		
		
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		
		driver.get("http://demo.guru99.com/test/web-table-element.php");
		
		driver.manage().window().maximize();
		
		List<WebElement> companyList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));




		TreeSet<String> sortedCompanyList = new TreeSet<>();

		for(WebElement companyName : companyList) {
			sortedCompanyList.add(companyName.getText());
		}

		System.out.println(Collections.max(sortedCompanyList));

		getacceptAlert().accept();
		
//		String max ;
//		double m=0,r=0;
//		System.out.println("Number of rows " + companyList.size());
//		ArrayList<Double> priceList = new ArrayList<>();
//
//		for(int i=1;i<=companyList.size();i++) {
//
//			String companyName = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr["+ i + "]/td[1]")).getText();
//			max = driver.findElement(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr["+ i + "]/td[3]")).getText();
//			System.out.println(companyName + "\t" + max);
////			NumberFormat f = NumberFormat.getNumberInstance();
////			Number num = f.parse(max);
////			max = num.toString();
////			m = Double.parseDouble(max);
////
////			if(m>r) {
////
////				r=m;
////			}
//			double prices = Double.parseDouble(max);
//			priceList.add(prices);
//		}
//		System.out.println(priceList.size());
//
//		double HighestPrice = Collections.max(priceList);
//		System.out.println("The largest price is " + HighestPrice);
	}

	public static Alert getacceptAlert(){


		Alert alert = driver.switchTo().alert();
		return alert;
	}


}
