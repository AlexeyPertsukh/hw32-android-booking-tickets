package com.example.bookingtickets;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");

    private Util() {
    }

    private final static String[] monthName = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября","декабря"};
    @SuppressLint("DefaultLocale")
    public static String dateToString(Date date) {
        return formatterDate.format(date);
//        return String.format("%d %s %d", date.getDay(), monthName[date.getMonth()], date.getYear() + 1900 );
    }
}
