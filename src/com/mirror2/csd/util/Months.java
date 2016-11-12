package com.mirror2.csd.util;

enum Months {
    Samsung(400), Nokia(250),Motorola(325);

    int value;
    Months(int p) {
        value = p;
    }
    int showPrice() {
        return value;
    }
}
