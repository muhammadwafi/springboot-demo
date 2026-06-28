package com.spring.demo.app.weather.client;

import com.spring.demo.app.common.http.client.ExternalApiClient;
import com.spring.demo.app.common.http.model.ApiRequest;
import com.spring.demo.app.weather.client.dto.WeatherApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherApiClientImpl implements WeatherApiClient {

    private final ExternalApiClient externalApiClient;

    @Override
    public WeatherApiResponse getCurrentWeather(
        double latitude,
        double longitude
    ) {
        ApiRequest<Void> request = ApiRequest
            .get("https://api.open-meteo.com/v1/forecast")
            .withQueryParam("latitude", latitude)
            .withQueryParam("longitude", longitude)
            .withQueryParam("current", "temperature_2m,relative_humidity_2m,weather_code");

        return externalApiClient.get(request, WeatherApiResponse.class);
    }

}
