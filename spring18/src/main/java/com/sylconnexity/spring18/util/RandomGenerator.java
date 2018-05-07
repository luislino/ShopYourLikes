package com.sylconnexity.spring18.util;

import java.util.Random;
import java.lang.StringBuilder;

public class RandomGenerator {
    private final String chars = "0123456789abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
    private Random rng;

    public RandomGenerator() {
        rng = new Random();
    }

    public Long generateRandomLong() {
        return rng.nextLong();
    }

    public Double generateRandomDouble() {
        return rng.nextDouble();
    }

    public Boolean generateRandomBoolean() {
        int randomInt = rng.nextInt(2);
        if (randomInt == 0)
            return false;
        else
            return true;
    }

    public String generateRandomString(int size) {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int randomInt = rng.nextInt(chars.length());
            randomString.append(chars.charAt(randomInt));
        }
        return randomString.toString();
    }

    public String generateRandomURL(int size) {
        StringBuilder randomURL = new StringBuilder();
        randomURL.append("https://www.");
        for (int i = 0; i < size; i++) {
            int randomInt = rng.nextInt(chars.length());
            randomURL.append(chars.charAt(randomInt));
        }
        randomURL.append(".com/");
        return randomURL.toString();
    }

    public String generateRandomDate() {
        int month = rng.nextInt(12) + 1;
        int day = rng.nextInt(31) + 1;
        int year = rng.nextInt(3000);

        StringBuilder randomDate = new StringBuilder();
        randomDate.append(month);
        randomDate.append("/");
        randomDate.append(day);
        randomDate.append("/");
        randomDate.append(year);
        return randomDate.toString();
    }

    public String generateRandomIP() {
        StringBuilder randomIP = new StringBuilder();
        for (int x = 0; x < 3; x++) {
            randomIP.append(rng.nextInt(255));
            randomIP.append(".");
        }
        randomIP.append(rng.nextInt(255));
        return randomIP.toString();
    }
}
