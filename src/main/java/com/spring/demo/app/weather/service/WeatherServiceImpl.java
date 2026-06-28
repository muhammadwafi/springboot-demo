package com.spring.demo.app.weather.service;

import com.spring.demo.app.weather.client.WeatherApiClient;
import com.spring.demo.app.weather.dto.WeatherResponse;
import com.spring.demo.app.weather.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApiClient apiClient;
    private final WeatherMapper mapper;

    @Override
    public WeatherResponse getCurrentWeather(
        double latitude,
        double longitude
    ) {
        return mapper.toResponse(
            apiClient.getCurrentWeather(latitude, longitude)
        );
    }
}
