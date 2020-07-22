import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class calender {

	
	public static void main(String[] args) {
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d,yyyy");
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		System.out.println(dateFormat.format(date));
		
	}
}
