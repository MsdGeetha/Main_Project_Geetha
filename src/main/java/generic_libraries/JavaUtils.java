package generic_libraries;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class JavaUtils {
	/**
	 * This method which is used to random int value
	 * @author GEETHA
	 * @return
	 */
	public int getRandom() {
		Random ran = new Random();
		int random = ran.nextInt(1000);
		return random;
	}
	/**
	 * This method which is used to get the current system date 
	 * @return
	 */
	public String systemDate() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String systemdate = d.format(date);
		return systemdate;
	}
	/**
	 * This Method which is used to get the current system date with Time
	 * @return
	 */
	public String sysdate() {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String systemDateInFormat = sim.format(date);
		return systemDateInFormat;
	}
	/**
	 * This method which is used to get the future month from the system date 
	 * @param futureMon
	 * @return
	 */
	public String futureDateIntermsOfMonths(long futureMon) {
		
		LocalDate date = LocalDate.now();
		LocalDate future = date.plusMonths(futureMon);
		
		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String futureMonth = d.format(future);
		return futureMonth;
	}
}
