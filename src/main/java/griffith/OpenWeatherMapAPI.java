package griffith;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenWeatherMapAPI {

	// Define the API key as a constant
	private static final String API_KEY = "da05380decb9578693f08f2b8f302dae";
	private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

	public OpenWeatherMapResponse getWeatherData(String city, String country) throws Exception {
		if (API_KEY == null || API_KEY.isEmpty()) {
			throw new IllegalArgumentException("API key is missing. Please set the API_KEY constant.");
		}

		// Construct the URI string
		String location = city + "," + country;
		String uriString = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, location, API_KEY);
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(URI.create(uriString))
				.GET()
				.build();

		// Create the HTTP client and send the request
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

		// Check for HTTP response status code
		if (getResponse.statusCode() != 200) {
			throw new RuntimeException("Failed to get weather data: HTTP Status Code " + getResponse.statusCode());
		}

		// Parse the response body
		String responseBody = getResponse.body();
		return parseWeatherData(responseBody);
	}

	private OpenWeatherMapResponse parseWeatherData(String responseBody) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(responseBody);

		// Check for error message in the response body
		if (rootNode.has("message")) {
			String errorMessage = rootNode.path("message").asText();
			if (!errorMessage.isEmpty()) {
				System.out.println("Error: " + errorMessage);
				return null;
			}
		}

		// Extract relevant data
		String weatherDescription = rootNode.path("weather").get(0).path("description").asText();
		double temperature = rootNode.path("main").path("temp").asDouble();
		double humidity = rootNode.path("main").path("humidity").asDouble();
		double windSpeed = rootNode.path("wind").path("speed").asDouble();

		// Create a response object with extracted data
		return new OpenWeatherMapResponse(weatherDescription, temperature, humidity, windSpeed);
	}
}