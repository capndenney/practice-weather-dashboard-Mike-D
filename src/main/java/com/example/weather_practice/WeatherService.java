package com.example.weather_practice;

import com.example.weather_practice.models.Forecast;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

    private final String apiKey;
    private final RestClient restClient;

    public WeatherService(@Value("${OPENWEATHERMAP_API_KEY}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.create();
    }

    public Forecast fetchForecast(String cityName, String lat, String lon) {
        return restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={key}&units=imperial&exclude=hourly,minutely,alerts",
                        lat, lon, apiKey)
                .retrieve()
                .body(Forecast.class);
    }
}