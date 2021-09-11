package com.example.bookingtickets;

import java.io.Serializable;
import java.util.Objects;

public class TripRoute implements Serializable {
    private final City fromCity;
    private final City toCity;

    private TripRoute(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public static TripRoute of(City fromCity, City toCity) {
        return new TripRoute(fromCity, toCity);
    }

    public String getStringRoute() {
        return String.format("%s - %s", fromCity.getNameCity(), toCity.getNameCity());
    }

    public String getNameCityStart() {
        return fromCity.getNameCity();
    }

    public String getNameCityFinish() {
        return toCity.getNameCity();
    }

}
