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


		//Test for 5 days from today.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_YEAR,5);
		String thirtyDaysFromNow =  dateFormat.format(calendar.getTime());
		assertTrue(chatbot.isDateWithinRange(inputDateToday), "Expected true but was false for 5 days from today.");

		//Test for date beyond 5 days from today.
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
	void testGetTemperature() {
		//Test for Valid Country and City.
		String country = "Russia";
		String city = "Moscow";
		String date = "2024-07-25";

		double temperature = Chatbot.getTemperature(country, city, date);
		assertFalse(Double.isNaN(temperature), "Expected a valid temperature but got NaN.");

		// Test with invalid date format
		String invalidDate = "2023-24-07";
		temperature = Chatbot.getTemperature(country, city, invalidDate);
		assertTrue(Double.isNaN(temperature), "Expected NaN for invalid date format.");

		// Test with non-existent city
		String invalidCountry="FakeCountry";
		String invalidCity = "FakeCity";
		temperature = Chatbot.getTemperature(invalidCountry, invalidCity, date);
		assertTrue(Double.isNaN(temperature), "Expected NaN for non-existent city.");
	}

	@Test
	void testGetTempSuggestions() {
		//Test for different Temperature Suggestions.
		assertEquals("\nIt's freezing! Wear a thick jacket, scarf, gloves and a warm hat.", Chatbot.getTempSuggestions(-1));
		assertEquals("\nIt's quite cold. Wear a coat and a warm hat.", Chatbot.getTempSuggestions(5));
		assertEquals("\nIt's cool. A hoodie and tracksuit bottoms would be fine.", Chatbot.getTempSuggestions(11));
		assertEquals("\nIt's warm. Wear comfortable clothes such as a tank top and shorts.", Chatbot.getTempSuggestions(25));
		assertEquals("\nIt's hot! Wear light clothes and stay hydrated.", Chatbot.getTempSuggestions(35));
	}
}
