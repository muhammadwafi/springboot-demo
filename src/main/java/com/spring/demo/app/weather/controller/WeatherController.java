package com.spring.demo.app.weather.controller;

import com.spring.demo.app.weather.model.City;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "jakarta") String city,
        Model model
    ) {

        City selectedCity = City.fromSlug(city);

        if (selectedCity == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Unknown city: " + city
            );
        }

        model.addAttribute("cities", City.values());
        model.addAttribute("selectedCity", selectedCity);

        return "weather/index";
    }
}
