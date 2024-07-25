package griffith;

import java.util.Scanner; // Import scanner.
import java.text.SimpleDateFormat; // Import Simple Date Format.
import java.util.Calendar;//Import Calendar.
import java.util.Date; // Import Date.
import java.text.ParseException; // Import Parse Exception.

public class Chatbot {

    public static Scanner scan = new Scanner(System.in); // Create an instance of scanner.
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); // Create a constant DATE_FORMAT.

    public static void main(String[] args) {
        String input = ""; // Create a variable which takes in inputs.

        while (true) {
            System.out.println("Greetings, I am yapMaxx. Would you like to get clothing suggestions for a specific location and date? (Y/N)"); // Prompt user to make an input.
            input = scan.nextLine(); // Read user input.

            if (input.equalsIgnoreCase("Y")) { // If Yes, Get input for country and city.
                System.out.println("Perfect! Please enter a country you're interested in."); // Prompt user to enter country.
                String country = scan.nextLine(); // Read User input for country.
                System.out.println("Now enter a city in this country."); // Prompt user to enter city.
                String city = scan.nextLine(); // Read user input for city.

                String inputDate = "";
                while (true) {
                    System.out.println("Enter a date within the next 5 days in the format (yyyy-MM-dd)"); // Prompt user to enter a date.
                    inputDate = scan.nextLine(); // Read user input for date.

                    if (isDateWithinRange(inputDate)) {
                        break;
                    } else {
                        System.out.print("Date is out of range. For forecasts within 30 days, Subscribe to OpenWeatherMap \"Developer\". \n\n");
                    }
                }

                System.out.println("This is the weather report for " + city.toUpperCase() + ", " + country.toUpperCase() + ", " + inputDate + "." + "\n"); // Generate Weather Report.
                getWeatherReport(country, city, inputDate); // Call getWeatherReport method.

                // Fetch temperature and print suggestions
                double temperature = getTemperature(country, city, inputDate); // Fetch the temperature
                if (!Double.isNaN(temperature)) {
                    String suggestions = getTempSuggestions(temperature); // Get clothing suggestions based on temperature
                    System.out.println(suggestions); // Print clothing suggestions
                }
                break;
            } else if (input.equalsIgnoreCase("N")) { // If User types "N".
                System.out.println("Goodbye."); // Print exit message.
                break; // Exit the loop
            } else { // Otherwise if an input is invalid,
                System.out.println("Invalid input, please try again."); // Print error Message.
            }
        }
    }

    public static void getWeatherReport(String country, String city, String inputDate) {
        OpenWeatherMapAPI weatherAPI = new OpenWeatherMapAPI(); // Create an instance of OpenWeatherAPI
        try {
            OpenWeatherMapResponse weatherResponse = weatherAPI.getWeatherData(country, city, inputDate); // Call API.
            if (weatherResponse != null) { // If response is not null,
                System.out.println(weatherResponse); // Print out response.
            } else {
                System.out.println("Failed to get weather report."); // Else Print Error message if response is null.
            }
        } catch (Exception e) { // If location is invalid,
            System.out.println(e.getMessage() + ", Please try again."); // Print specific error message.
        }
    }

    public static boolean isDateWithinRange(String inputDate) {
        try {
            Date date = DATE_FORMAT.parse(inputDate); // Parse inputDate.
            Date today = new Date(); // Get today's date.

            // Reset the time part to 00:00:00.000 for accurate comparison
            Calendar todayCalendar = Calendar.getInstance();
            todayCalendar.setTime(today);
            todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
            todayCalendar.set(Calendar.MINUTE, 0);
            todayCalendar.set(Calendar.SECOND, 0);
            todayCalendar.set(Calendar.MILLISECOND, 0);
            today = todayCalendar.getTime();

            // Calculate 30 days from today.
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DATE, 5);
            Date fiveDaysfromNow = c.getTime();

            // Check if the date is within range.
            return !date.before(today) && !date.after(fiveDaysfromNow);
        } catch (ParseException e) {
            // Handle invalid date format
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return false;
        }
    }

    // Modified method to get temperature for a specific date
    public static double getTemperature(String country, String city, String date) {
        OpenWeatherMapAPI weatherAPI = new OpenWeatherMapAPI(); // Create an instance of OpenWeatherMapAPI.
        try {
            OpenWeatherMapResponse weatherResponse = weatherAPI.getWeatherData(country, city, date); // Call API to get temperature
            if (weatherResponse != null) {
                return weatherResponse.getTemperature();
            } else {
                System.out.println("No weather data available for the specified date.");
                return Double.NaN; // Return NaN if no data is available
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + ", Please try again."); // Print specific error message.
            return Double.NaN; // Return NaN in case of an error
        }
    }

    public static String getTempSuggestions(double temperature) {
        if (temperature < 0) { //Suggestion for below 0 degrees.
            return "\nIt's freezing! Wear a thick jacket, scarf, gloves and a warm hat.";
        } else if (temperature < 10) {//Suggestion for below 10 degrees.
            return "\nIt's quite cold. Wear a coat and a warm hat.";
        } else if (temperature < 20) {//Suggestion for below 20 degrees.
            return "\nIt's cool. A hoodie and tracksuit bottoms would be fine.";
        } else if (temperature < 30) {//Suggestion for below 30 degrees.
            return "\nIt's warm. Wear comfortable clothes such as a tank top and shorts.";
        } else {//Suggestion for over 30 degrees.
            return "\nIt's hot! Wear light clothes and stay hydrated.";
        }
    }
}