package com.spring.demo.app.weather.controller;

import com.spring.demo.app.weather.dto.WeatherResponse;
import com.spring.demo.app.weather.model.City;
import com.spring.demo.app.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class WeatherRestController {

    private final WeatherService weatherService;

    @GetMapping("/api/weather")
    public WeatherResponse weather(
        @RequestParam(defaultValue = "jakarta") String city
    ) {
        City selectedCity = City.fromSlug(city);

        if (selectedCity == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Unknown city: " + city
            );
        }

        return weatherService.getCurrentWeather(
            selectedCity.getLatitude(),
            selectedCity.getLongitude()
        );
    }

}
