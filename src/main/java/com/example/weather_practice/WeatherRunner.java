package com.example.weather_practice;

import org.springframework.boot.CommandLineRunner;
import com.example.weather_practice.models.Forecast;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class WeatherRunner implements CommandLineRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private final WeatherService weatherService;

    public WeatherRunner(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void run(String... args) {
        int choice;
        do {
            System.out.println("\n=== Weather Forecast Menu ===");
            System.out.println("1. Forecast for Kansas City");
            System.out.println("2. Forecast for St. Louis");
            System.out.println("3. Forecast for Philadelphia");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayForecast("Kansas City", "39.0997", "-94.5783");
                    break;
                case 2:
                    displayForecast("St. Louis", "38.6274", "-90.1982");
                    break;
                case 3:
                    displayForecast("Philadelphia", "39.9526", "-74.1652");
                    break;
                case 4: System.exit(0);
            }
        } while (choice != 4);

        scanner.close();
    }

    private void displayForecast(String cityName, String lat, String lon) {
        try {
            Forecast forecast = weatherService.fetchForecast(cityName, lat, lon);
            System.out.println("Weather forecast for " + cityName + ": " + forecast);
        } catch (Exception e) {
            System.out.println("Error fetching forecast for " + cityName + ": " + e.getMessage());
        }
    }

}