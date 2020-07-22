import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReports {
		 

	@Test
 	public static void beforeTest() {
      
		ExtentHtmlReporter Htmlreport = new ExtentHtmlReporter("extentReport.html");
		
		ExtentReports extent = new ExtentReports();

		
	}

}
