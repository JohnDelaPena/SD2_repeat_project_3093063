package griffith;

import java.util.Scanner;//Import scanner.

public class Chatbot {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String input = ""; //Create a variable which takes in inputs.

        while (true) {
            System.out.println("Greetings, I am yapMaxx. Would you like to get clothing suggestions for a specific date? (Y/N)"); //Prompt user to make an input.
            input = scan.nextLine(); //Read user input.

            if (input.equalsIgnoreCase("Y")) {//If Yes, Get input for country and city.
                System.out.println("Perfect! Please enter a country you're interested in.");//Prompt user o enter country.
                String country = scan.nextLine();//Read User input for country.
                System.out.println("Now enter a city in this country.");//Prompt user to enter city.
                String city = scan.nextLine(); // Read user input for city.
                // You can add more code here to process the city and country
                break;
            } else if (input.equalsIgnoreCase("N")) { //If User types "N".
                System.out.println("Goodbye."); //Print exit message.
                break; // Exit the loop
            } else {// Otherwise if an input is invalid,
                System.out.println("Invalid input, please try again.");//Print error Message.
            }
        }
    }
}