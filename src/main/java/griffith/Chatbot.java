package griffith;

import java.util.Scanner;//Import scanner.
import java.text.SimpleDateFormat;//Import Simple Date Format.
import java.util.Date;//Import Date.
import java.text.ParseException; //Import Parse Exception.

public class Chatbot {

	public static Scanner scan = new Scanner(System.in); //Create an instance of scanner.
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); //Create a constant DATE_FORMAT.
	
	public static void main(String[] args) {
		String input = ""; //Create a variable which takes in inputs.
		

		while (true) {
			System.out.println("Greetings, I am yapMaxx. Would you like to get clothing suggestions for a specific location and date? (Y/N)"); //Prompt user to make an input.
			input = scan.nextLine(); //Read user input.

			if (input.equalsIgnoreCase("Y")) {//If Yes, Get input for country and city.
				System.out.println("Perfect! Please enter a country you're interested in.");//Prompt user o enter country.
				String country = scan.nextLine();//Read User input for country.
				System.out.println("Now enter a city in this country.");//Prompt user to enter city.
				String city = scan.nextLine(); // Read user input for city.
				System.out.println("Enter a date within then next 30 days in the format (YYYY-MM-DD)");//Prompt user to enter a date.
				String inputDate = scan.nextLine();//Read user input for date.
				
				 // Validate date format
                Date date;
                try {
                    date = DATE_FORMAT.parse(inputDate);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    continue; // Retry the loop
                }
				
				
				System.out.println("This is the weather report for " +city.toUpperCase() +", " +country.toUpperCase() +"." +"\n");//Generate Weather Report.
				getWeatherReport(city,country,inputDate);//Call getWeatherReport method.
				break;
			} else if (input.equalsIgnoreCase("N")) { //If User types "N".
				System.out.println("Goodbye."); //Print exit message.
				break; // Exit the loop
			} else {// Otherwise if an input is invalid,
				System.out.println("Invalid input, please try again.");//Print error Message.
			}
		}
	}

	public static void getWeatherReport(String city, String country,String inputDate) {
		
		OpenWeatherMapAPI weatherAPI = new OpenWeatherMapAPI();//Create an instance of OpenWeatherAPI
		try {
			OpenWeatherMapResponse weatherResponse = weatherAPI.getWeatherData(city, country, inputDate); //Call API.
			if (weatherResponse != null) { //If response is not null,
				System.out.println(weatherResponse); //Print out response.
			} else {
				System.out.println("Failed to get weather report."); //Else Print Error message if response is null.
			}
		} catch (Exception e) { // If location is invalid,
			System.out.println(e.getMessage() +	", Please Try again."); // Print specific error message.
		}
	}
	
	public static boolean isDateWithinRange() {
		return false;
	}
	
	
	public static boolean isDateValid(Date date) {
		return false;
	}
	
	public static double getTemperature() {
		return 0;
	}
	
	public static String getTempSuggestions(double temperature) {
		return "";
	}
}