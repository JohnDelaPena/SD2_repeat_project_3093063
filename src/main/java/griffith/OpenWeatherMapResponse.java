package griffith;

public class OpenWeatherMapResponse {

	private String weatherDescription; //Created String a variable for weather description.
	private double temperature; //Created String a variable for temperature.
	private double humidity;//Created String a variable for humidity.
	private double windSpeed; //Created String a variable for wind speed.

	public OpenWeatherMapResponse(String weatherDescription, double temperature, double humidity, double windSpeed) { //Created constructor.
		//Assigned variables to the instance variables.
		this.weatherDescription = weatherDescription;
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
	}

	//Getter for weather description.
	public String getWeatherDescription() {
		return weatherDescription;
	}
	//Getter for temperature.
	public double getTemperature() {
		return temperature;
	}

	//Getter for humidity.
	public double getHumidity() {
		return humidity;
	}

	//Getter for weather wind speed. {
	public double getWindSpeed() {
		return windSpeed;
	}

	@Override
	public String toString() {
		return String.format("Weather: %s\nTemperature: %.2f°C\nHumidity: %.2f%%\nWind Speed: %.2f m/s",
				weatherDescription, temperature, humidity, windSpeed);
	}
}	