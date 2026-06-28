package com.spring.demo.app.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherApiResponse {

    @JsonProperty("current")
    private WeatherCurrentApiResponse current;

}