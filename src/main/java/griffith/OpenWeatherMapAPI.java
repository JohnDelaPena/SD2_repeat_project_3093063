package griffith;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenWeatherMapAPI {

    private static final String API_KEY = "da05380decb9578693f08f2b8f302dae"; //My API Key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    public OpenWeatherMapResponse getWeatherData(String city, String country, String date) throws Exception {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalArgumentException("API key is missing. Please set the API_KEY constant.");
        }

        String location = city + "," + country;
        String uriString = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, location, API_KEY);
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(uriString))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (getResponse.statusCode() != 200) {
            throw new RuntimeException("Failed to get weather data: HTTP Status Code " + getResponse.statusCode());
        }

        String responseBody = getResponse.body();
        return parseWeatherData(responseBody, date);
    }

    private OpenWeatherMapResponse parseWeatherData(String responseBody, String date) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        
        JsonNode listNode = rootNode.path("list");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date targetDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        for (JsonNode node : listNode) {
            Date forecastDate = sdf.parse(node.path("dt_txt").asText());
            if (isSameDay(targetDate, forecastDate)) {
                String weatherDescription = node.path("weather").get(0).path("description").asText();
                double temperature = node.path("main").path("temp").asDouble();
                double humidity = node.path("main").path("humidity").asDouble();
                double windSpeed = node.path("wind").path("speed").asDouble();
                return new OpenWeatherMapResponse(weatherDescription, temperature, humidity, windSpeed);
            }
        }
        return null; // Return null if no matching forecast found
    }

    private boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date1).equals(sdf.format(date2));
    }
}