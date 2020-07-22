import static org.testng.Assert.assertTrue;

import java.util.Locale;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import com.github.javafaker.PhoneNumber;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class fakerTest {

	static Faker faker;
	static FakeValuesService fakevalueservice;
	static StringJoiner joiner;
	static StringBuilder strbuild;
	static Random rand;


	public String getFirstName() {

		faker =  new Faker();
		joiner = new StringJoiner(" ");
		joiner.add(faker.name().firstName()).add(faker.name().lastName());

		return joiner.toString();
	}

	// to Generate ID number
	public static String getRandomID() {

		IdNumber ID = faker.idNumber();

		return ID.toString();
	}

	// to Generate Random phoneNumber
	public  String getPhoneNumber() {

		faker = new Faker(new Locale("en-IND"));

		PhoneNumber number = faker.phoneNumber();

		String phno = number.phoneNumber().replaceAll("-", "");

		return phno;
	}

	// to generate random ID
	public static String generateId() {

		rand = new Random();

		int randomDigit = rand.nextInt(500);

		strbuild = new StringBuilder();
		
		strbuild.append("MSE")
		.append("-")
		.append(randomDigit);
		
		return strbuild.toString();
	}

	// to generate Adress
	public static String getAddress() {

		faker = new Faker(new Locale("en-IND"));

		joiner = new StringJoiner(",");
		joiner.add(faker.address().buildingNumber()).add(faker.address().streetAddress())
				.add(faker.address().cityName()).add("India").add(faker.address().zipCode());

		return joiner.toString();
	}
	
	//to generate EmailAddress 
	public static String getEmailAddress() {
		
		StringBuilder strbuilder = new StringBuilder();
		
		strbuilder.append("rajesh.ganessan+")
		.append(generateId())
		.append("@letsventure.com");
		
		return strbuilder.toString();
	}
	
	//to generate name
	
		public static String getFundingRoundName() {
			
			
			fakevalueservice = new FakeValuesService(new Locale("en-GB"),  new RandomService());
			
			String fundingName = fakevalueservice.numerify("Seed-round###");
			return fundingName;
			}
		
		public static void whenBothifyCalled_checkPatternMatches() throws Exception {
			 
		    FakeValuesService fakeValuesService = new FakeValuesService(
		      new Locale("en-GB"), new RandomService());
		 
		    String email = fakeValuesService.bothify("????##@gmail.com");
		    Matcher emailMatcher = Pattern.compile("\\w{4}\\d{2}@gmail.com").matcher(email);
		    
		    System.out.println(email);
		    assertTrue(emailMatcher.find());
		}

		
		//generating Postman
		public static String getPostMoney() {
			
			double random = ThreadLocalRandom.current().nextDouble(30.5, 40.5);
			double roundedRandom = Math.round(random * 100D) / 100D;
			return String.valueOf(roundedRandom);
		}
		
		//to Generate shareHolder
		public static String getFundingShares() {

	  
	        int rand_int1 = rand.nextInt(1000000); 
	        
	        
	        return String.valueOf(rand_int1);
		}
		


}
