package com.spring.demo.app.weather.mapper;

import com.spring.demo.app.weather.client.dto.WeatherApiResponse;
import com.spring.demo.app.weather.client.dto.WeatherCurrentApiResponse;
import com.spring.demo.app.weather.dto.WeatherCurrentResponse;
import com.spring.demo.app.weather.dto.WeatherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherResponse toResponse(WeatherApiResponse response);

    @Mapping(target = "temperature", source = "temperature2m")
    @Mapping(target = "humidity", source = "relativeHumidity2m")
    WeatherCurrentResponse toResponse(WeatherCurrentApiResponse current);

}
