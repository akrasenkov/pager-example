package edu.mirea;

public class Assert {
    static void checkNotEven(int number, String message) {
        if (number % 2 == 0 && number != 1) {
            throw new IllegalArgumentException(message);
        }
    }

    static void checkNotLess(int a, int b, String message){
        if (a < b) {
            throw new IllegalArgumentException(message);
        }
    }

    static void checkNotZero(int number, String message) {
        if (number == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    static void checkNotNegative(int number, String message) {
        if (number < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
