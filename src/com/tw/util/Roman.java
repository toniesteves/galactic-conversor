package com.tw.util;

/**
 *
 * @author Toni
 */
public enum Roman {

    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
    private final int number;

    private Roman(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
};
