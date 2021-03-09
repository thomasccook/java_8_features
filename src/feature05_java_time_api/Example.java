package feature05_java_time_api;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

public class Example {

			
	/////////////////////////////////////////////////
	// java.time.LocalDate
	
	// @Test
	public void localDate() {

		LocalDate date = null;
		
		date = LocalDate.now();
		prn("Now", date);
		
		date = LocalDate.of(2014, Month.JANUARY, 1);
		prn("Another date", date);
		
		date = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		prn("Date in a different timezone", date);
		
		date = LocalDate.ofYearDay(2014, 100);
		prn("Date, 100 days from beginning of year", date);
		
	}

	
	/////////////////////////////////////////////////
	// java.time.LocalTime
	
	// @Test
	public void localTime() {

		LocalTime time = null;
		
		time = LocalTime.now();
		prn("Now", time);

		time = LocalTime.of(12,20,25,40);
		prn("Another time", time);
		
		time = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		prn("Time in a different timezone", time);
		
		time = LocalTime.ofSecondOfDay(10000);
		prn("Time, 10000 seconds from beginning of day", time);
		
	}
	

	/////////////////////////////////////////////////
	// java.time.LocalDateTime
	
	// @Test
	public void localeDateTime() {
		
		LocalDateTime dateTime = null;
		
		dateTime = LocalDateTime.now();
		prn("Now", dateTime);

		dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		prn("Another DateTime", dateTime);
		
		dateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		prn("Datetime in a different timezone", dateTime);
		
		dateTime = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
		prn("DateTime, 10000 seconds since the beginning of Epoch time", dateTime);
		
	}

	
	/////////////////////////////////////////////////
	// java.time.Instant
	//
	// Instant class is used to work with machine readable time format, 
	//   it stores date time in unix timestamp. ???
	
	// @Test
	public void instant() {
		
		LocalDateTime dateTime = LocalDateTime.now();
		prn("Now", dateTime);		
		
		
		Instant timestamp = Instant.now();
		prn("Now", timestamp);
	}	
	
	
	/////////////////////////////////////////////////
	// Duration
	// The duration of time in ISO-8601 format
	// 
	
	// @Test
	public void duration() {

		Duration duration = null;
		
		duration = Duration.ofSeconds(30);
		prn("30 seconds", duration);
		
		duration = Duration.ofMinutes(30);
		prn("30 minutes", duration);
		
		duration = Duration.ofHours(6);
		prn("6 hours", duration);
		
		duration = Duration.ofDays(7);
		prn("7 days", duration);
		
		duration = Duration.ofDays(365);
		prn("365 days", duration);		
		
	}		
	
	// https://stackoverflow.com/questions/51168022/what-does-pt-prefix-stand-for-in-duration	
	//	P is the duration designator (for period) placed at the start of the duration representation.
	//	T is the time designator that precedes the time components of the representation.	
	//	Y is the year designator that follows the value for the number of years.
	//	M is the month designator that follows the value for the number of months.
	//	W is the week designator that follows the value for the number of weeks.
	//	D is the day designator that follows the value for the number of days.

	
	/////////////////////////////////////////////////
	// Mathematics
	// 

	
	// @Test
	public void mathematics() {

		LocalDate today = LocalDate.now();
		
		prn("Is leap year?", today.isLeapYear());
		
		prn("Is before 01/01/2015?", today.isBefore(LocalDate.of(2015,1,1)));
		
		prn("Create LocalDateTime from LocalDate", today.atTime(LocalTime.now()));
		
		prn("10 days after today will be ", today.plusDays(10));
		prn("3 weeks after today will be ", today.plusWeeks(3));
		prn("20 months after today will be ", today.plusMonths(20));
		
		prn("10 days before today will be ", today.minusDays(10));
		prn("3 weeks before today will be ", today.minusWeeks(3));
		prn("20 months before today will be ", today.minusMonths(20));
				
	}	
	
	/////////////////////////////////////////////////
	// java.time.temporal.TemporalAdjusters
	
	// @Test
	public void temporalAdjusters() {
		
		LocalDate today = LocalDate.now();
		
		TemporalAdjuster temporalAdjuster = null;
		
		temporalAdjuster = TemporalAdjusters.firstDayOfMonth();
		prn("First date of this month", today.with(temporalAdjuster));
		
		temporalAdjuster = TemporalAdjusters.lastDayOfYear();
		prn("Last date of this year", today.with(temporalAdjuster));
		
	}
	
	/////////////////////////////////////////////////
	// java.time.Period
	
	// @Test
	public void period() {
		
		LocalDate today = LocalDate.now();
		LocalDate later = LocalDate.ofYearDay(2021, 365);
		
		Duration duration = Duration.ofDays(365);
		prn("365 days as Duration", duration);				
		
		Period period = today.until(later);
		prn("365 days as Period", period);
		
	}
	
	
	/////////////////////////////////////////////////
	// java.time.format.DateTimeFormatter
	
	// @Test
	public void parsingAndFomatting() {
		
		LocalDate date = LocalDate.now();

		prn("Default format of LocalDate", date);
		prn("Weird Format", date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
		prn("ISO Format", date.format(DateTimeFormatter.ISO_DATE));
		
		prn();
		
		LocalDateTime dateTime = LocalDateTime.now();
		prn("Default format of LocalDateTime", dateTime);
		prn("Weird Format", dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
		prn("ISO Format", dateTime.format(DateTimeFormatter.ISO_DATE_TIME));
		
		prn();
		
		Instant timestamp = Instant.now();
		prn("Default format of Instant", timestamp);
		
		prn();
		
		LocalDateTime dt = LocalDateTime.parse("27::Apr::2014 21::39::48",
				DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
		prn("Parsed date", dt);
		
	}
		
	/////////////////////////////////////////////////
	// Conversion
	// java.time.* to java.time.*
	// Legacy to java.time.*
	// java.time.* to Legacy
	
	// @Test
	public void legacy() {
		
		Instant timestamp = new Date().toInstant();
		LocalDateTime date = LocalDateTime.ofInstant(timestamp, ZoneId.of(ZoneId.SHORT_IDS.get("PST")));
		prn("java.time.Instant -> java.time.LocalDateTime", date);
		
		Instant time = Calendar.getInstance().toInstant();
		prn("java.util.Calendar -> java.time.Instance", time);

		ZoneId defaultZone = TimeZone.getDefault().toZoneId();
		prn("java.util.TimeZone -> java.time.ZoneId", defaultZone);
		
		ZonedDateTime gregorianCalendarDateTime = (new GregorianCalendar()).toZonedDateTime();
		prn("java.util.GregorianCalendar -> java.time.ZonedDateTime", gregorianCalendarDateTime);
		
		Date dt = Date.from(Instant.now());
		prn("java.time.Instant -> java.util.Date", dt);
		
		TimeZone tz = TimeZone.getTimeZone(defaultZone);
		prn("java.time.ZoneId -> java.util.TimeZone", tz);
		
		GregorianCalendar gc = GregorianCalendar.from(gregorianCalendarDateTime);
		prn("java.time.ZonedDateTime -> java.util.GregorianCalendar", gc);
		
	}	
	
	
	@Test
	public void localDateTime_Vs_zonedDateTime() {

		LocalDateTime localDateTime = LocalDateTime.now();
		prn("Now", localDateTime);
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		prn("Now", zonedDateTime);
	}
	
	
	
	/////////////////////////////////////////////////////
	// Helper Functions
	
	private void prn() {
		System.out.println("---");
	}
	
	// A more pleasant looking print function
	private void prn(String title, Object obj) {
		System.out.println(title + ":");
		System.out.println("   "+ obj);
	}	

	
		
}