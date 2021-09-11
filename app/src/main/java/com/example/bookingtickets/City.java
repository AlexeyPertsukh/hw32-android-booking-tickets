package com.example.bookingtickets;

import java.io.Serializable;

public enum City  implements Serializable {
    ZP("Запорожье"),
    Kiev("Киев"),
    Dnepr("Днепр"),
    ;

    private final String nameCity;

    City(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNameCity() {
        return nameCity;
    }
}
