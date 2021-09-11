package com.example.bookingtickets;

public class Amount {
    private int num;

    public Amount(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void inc() {
        num++;
    }

    public void dec() {
        num--;
        if(num < 1) {
            num = 1;
        }

    }


    public void reset() {
        num = 1;
    }



}
