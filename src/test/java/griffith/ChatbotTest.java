package griffith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		
		//Dates are in the format YYYY-MM-DD
		
		String inputDate="2024-07-23";  //Test for today.
		assertTrue(chatbot.isDateWithinRange(inputDate));
		
		String inputDate2="2024-08-22"; //Test for 30 days from today.
		assertTrue(chatbot.isDateWithinRange(inputDate2));
		
		String inputDate3="2024-09-23"; //Test for more than 30 days from today.
		assertTrue(chatbot.isDateWithinRange(inputDate3));
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
