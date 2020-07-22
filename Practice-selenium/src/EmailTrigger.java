import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EmailTrigger {

	public static void main(String[] args) throws EmailException, MalformedURLException {

		final String appPass = "rffgoyvwpqpykixf";

		String htmlContent = "<!DOCTYPE html><html lang=\"en\">"
				+ " <head> <meta charset=\"UTF-8\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /> <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" /> <title>MyStartupEquity</title> "
				+ "<style> p { margin: 0px; } </style> </head> <body> <p> "
				+ "<img style=\"display: block; margin-left: auto; margin-right: auto;\""
				+ " src=\"https://s3.ap-south-1.amazonaws.com/static.mystartupequity.com/assets/esop-logo.png\" "
				+ "alt=\"MyStartupEquity\" width=\"150\" height=\"50\" /> </p> <p>&nbsp;</p> "
				+ "<div style=\"margin: 10px;\"> <strong><p>Hello Team, </p><strong>"
				+ " <p>&nbsp;</p> <p><span style=\"font-weight: 400;\">Greetings from MyStartupEquity!</span></p> <p>&nbsp;</p> <p>"
				+ " <span style=\"font-weight: 400;\" >The My Startup Equity Automation Suite has been Executed Successfully</span > </p> <p>&nbsp;</p> <p> "
				+ "<span style=\"font-weight: 400;\" > Hereby attaching the Execution Report from the Automation Suite. </span> </p> <p>&nbsp;</p> <p> <span style=\"font-weight: 400;\" >Kindly find the attached Report, revert back in case of queries</span > "
				+ "</p> <p>&nbsp;</p> <p><span style=\"font-weight: 400;\">Regards,</span></p> <p><span style=\"font-weight: 400;\">Team MyStartupEquity</span></p> </div> </body></html>'\n";
		  URL url = new URL("https://s3.ap-south-1.amazonaws.com");
		String filePath = lastFileModified(System.getProperty("user.dir") + "/Reports");

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.gmail.com");
//		email.setSmtpPort(587);
//		email.setAuthenticator(new DefaultAuthenticator("rajeshg.gmx@gmail.com", appPass));
//		email.setSSLOnConnect(true);
//		email.setFrom("rajesh@gmail.com");
//		email.setSubject("Checking Email API");
//		email.setMsg("This is a Test Email.Checking the Email API for reports.-)");
//		email.addTo("grajeshr97@gmail.com");
//		email.send();
//		System.out.println("Email sent Successfully");
		System.out.println("Execution started");

		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(filePath);
		;
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("MyStartUp Equity-Automation Suite Report");
		attachment.setName("MyStartUp Equity-Automation Suite Report.html");

		// Create the email message
//		MultiPartEmail email = new MultiPartEmail();
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setSSLOnConnect(true);	
		email.setAuthenticator(new DefaultAuthenticator("rajeshg.gmx@gmail.com", appPass));
		email.addTo("grajeshr97@gmail.com", "Rajesh G");
		email.setFrom("rajesh@LetsVenture.com", "My	StartupEquity");
		email.setSubject("MyStartUp Equity-Automation Suite Report");
//		email.setMsg("Hi Team, " + "My StartUp Equity Automation Suite has been executed successfully.Please find the "
//				+ "attached Report File for the reference");
		email.setHtmlMsg(htmlContent);
		
		email.setTextMsg("Your email client does not support HTML messages");
		

		email.attach(attachment);
		email.send();
		System.out.println("Email sent Successfully");
		System.out.println(filePath);

	}

	//Getting last modified file
	public static String lastFileModified(String dir) {
		File fl = new File(dir);
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		long lastMod = Long.MIN_VALUE;
		File choice = null;
		for (File file : files) {
			if (file.lastModified() > lastMod) {
				choice = file;
				lastMod = file.lastModified();
			}
		}
		return choice.getAbsoluteFile().toString();
	}
}