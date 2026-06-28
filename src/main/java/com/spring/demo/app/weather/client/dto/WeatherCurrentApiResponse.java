package com.spring.demo.app.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherCurrentApiResponse {

    @JsonProperty("temperature_2m")
    private Double temperature2m;

    @JsonProperty("relative_humidity_2m")
    private Integer relativeHumidity2m;

    @JsonProperty("weather_code")
    private Integer weatherCode;

}