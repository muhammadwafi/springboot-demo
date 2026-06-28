package com.spring.demo.app.weather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum City {

    JAKARTA(
        "jakarta",
        "Jakarta",
        -6.2088,
        106.8456
    ),

    BANDUNG(
        "bandung",
        "Bandung",
        -6.9175,
        107.6191
    ),

    SURABAYA(
        "surabaya",
        "Surabaya",
        -7.2575,
        112.7521
    ),

    YOGYAKARTA(
        "yogyakarta",
        "Yogyakarta",
        -7.7956,
        110.3695
    ),

    MALANG(
        "malang",
        "Malang",
        -7.983908,
        112.621391
    );

    private final String slug;
    private final String label;
    private final double latitude;
    private final double longitude;

    /**
     * Find city by URL slug.
     */
    public static City fromSlug(String slug) {
        return Arrays.stream(values())
            .filter(city -> city.slug.equalsIgnoreCase(slug))
            .findFirst()
            .orElse(null);
    }

}
