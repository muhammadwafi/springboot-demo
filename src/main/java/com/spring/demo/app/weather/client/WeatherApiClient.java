package com.spring.demo.app.weather.client;

import com.spring.demo.app.weather.client.dto.WeatherApiResponse;

public interface WeatherApiClient {

    WeatherApiResponse getCurrentWeather(
        double latitude,
        double longitude
    );

}
