package com.spring.demo.app.weather.dto;

import lombok.Data;

@Data
public class WeatherCurrentResponse {

    private Double temperature;

    private Integer humidity;

    private Integer weatherCode;

}
