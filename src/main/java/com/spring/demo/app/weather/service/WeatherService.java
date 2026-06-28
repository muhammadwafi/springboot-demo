package com.spring.demo.app.weather.service;

import com.spring.demo.app.weather.dto.WeatherResponse;

public interface WeatherService {

    WeatherResponse getCurrentWeather(
        double latitude,
        double longitude
    );

}
