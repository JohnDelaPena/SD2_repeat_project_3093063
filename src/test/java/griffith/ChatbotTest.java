package griffith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


class ChatbotTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIsDateWithinRange() {
		Chatbot chatbot= new Chatbot();//Create an instance of chatbot.
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


		// Test for today
		Date today = new Date();
		String inputDateToday = dateFormat.format(today);
		assertTrue(chatbot.isDateWithinRange(inputDateToday), "Expected true but was false for today's date");


		//Test for 30 days from today.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_YEAR, 30);
		String thirtyDaysFromNow =  dateFormat.format(calendar.getTime());
		assertTrue(chatbot.isDateWithinRange(inputDateToday), "Expected true but was false for 30 days from today.");

		//Test for date beyond 30 days from today.
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_YEAR, 31);
		String beyondThirtyDaysFromNow =  dateFormat.format(calendar.getTime());
		assertFalse(chatbot.isDateWithinRange(beyondThirtyDaysFromNow), "Expected false but was true for 31 days from today.");

		//Test for date less than today.
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		String lessThanToday =  dateFormat.format(calendar.getTime());
		assertFalse(chatbot.isDateWithinRange(lessThanToday), "Expected false but was true for days less today.");



	}

	@Test
	void testIsDateValid() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTemperature() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTempSuggestions() {
		fail("Not yet implemented");
	}

}
