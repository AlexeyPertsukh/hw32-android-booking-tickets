package com.example.bookingtickets;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bus implements Serializable {

    private static final String SPLIT_DATE_TIME = ":";
    private static final String SPLIT_TIME = "\\.";

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy");

    private final TripRoute tripRoute;
    private final Date dateStart;
    private final Date dateFinish;
    private final Time timeStart;
    private final Date timeFinish;
    private final double price;
    private int amountTickets;

    private Bus(TripRoute tripRoute, Date dateStart, Time timeStart, Date dateFinish, Date timeFinish, double price, int amountTickets) {
        this.tripRoute = tripRoute;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.price = price;
        this.amountTickets = amountTickets;
    }

    public static Bus of(TripRoute tripRoute, String stringDateTimeStart, String stringDateTimeFinish, double price, int amountTickets) {
        Date dateStart = stringToDate(stringDateTimeStart);
        Time timeStart = stringToTime(stringDateTimeStart);

        Date dateFinish = stringToDate(stringDateTimeFinish);
        Time timeFinish = stringToTime(stringDateTimeFinish);

        return new Bus(tripRoute, dateStart, timeStart, dateFinish, timeFinish, price, amountTickets);
    }



    public TripRoute getBus() {
        return tripRoute;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public double getPrice() {
        return price;
    }

    public String getFormattedDateStart() {
        return formatterDate.format(dateStart);
    }

    public String getFormattedDateFinish() {
        return formatterDate.format(dateFinish);
    }

    public String getFormattedTimeStart() {
        return formatterTime.format(timeStart);
    }

    public String getFormattedTimeFinish() {
        return formatterTime.format(timeFinish);
    }

    @SuppressLint("SimpleDateFormat")
    private static Date stringToDate(String stringDateTime) {
        String[] strings =  stringDateTime.split(SPLIT_DATE_TIME);

        Date date= null;
        try {
            date = formatterDate.parse(strings[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static Time stringToTime(String stringDateTime) {
        String[] strings =  stringDateTime.split(SPLIT_DATE_TIME);
        String[] stringsTime =  strings[1].split(SPLIT_TIME);

        int hour = Integer.parseInt(stringsTime[0]);
        int min = Integer.parseInt(stringsTime[1]);
        Time time = new Time(hour, min, 0);
        return time;
    }

    public int getAmountTickets() {
        return amountTickets;
    }

    public void amountTicketsDec() {
        amountTickets--;
    }

    public String getCityStart() {
        return tripRoute.getNameCityStart();
    }

    public String getCityFinish() {
        return tripRoute.getNameCityFinish();
    }

    public String getStringRoute() {
        return tripRoute.getStringRoute();
    }

    public boolean checkActualForTrip(String from, String to, Date date) {
        return tripRoute.getNameCityStart().equals(from) && tripRoute.getNameCityFinish().equals(to);
    }

    public String getFormattedWayTime() {
//        long num = timeFinish.getTime() - timeStart.getTime();
//        Time time = new Time(num);
        //todo костыль
        int minutes = (timeFinish.getHours() * 60 + timeFinish.getMinutes()) - (timeStart.getHours() * 60 + timeStart.getMinutes());
        int hour = minutes / 60;
        minutes = minutes - (hour * 60);
        return String.format("%02d:%02d",hour, minutes);
    }



    //
    /*
    public static class DTime {
        private Date date;
        private Time time;

        public DTime(Date date, Time time) {
            this.date = date;
            this.time = time;
        }

        public DTime addTime(Time addTime) {
            long longDateTime = date.getTime() + time.getTime() + addTime.getTime();
            Date newDate = new Date(longDateTime);

            System.out.println(newDate);
            return new DTime(newDate, new Time(longDateTime));
        }

        public Date getDate() {
            return date;
        }

        public Time getTime() {
            return time;
        }
    }
    */


}
