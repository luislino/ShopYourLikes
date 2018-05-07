package com.sylconnexity.spring18.util;

import java.util.Random;
import java.lang.StringBuffer;

public class RandomGenerator {
    private final String CHARS = "0123456789abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
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
        StringBuffer randomString = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
            int randomInt = rng.nextInt(CHARS.length());
            randomString.append(CHARS.charAt(randomInt));
        }
        return randomString.toString();
    }

    public String generateRandomURL(int size) {
        final String PREFIX = "https://www.";
        final String POSTFIX = ".com/";
        StringBuffer randomURL = new StringBuffer(size + PREFIX.length() + POSTFIX.length());
        randomURL.append(PREFIX);
        for (int i = 0; i < size; i++) {
            int randomInt = rng.nextInt(CHARS.length());
            randomURL.append(CHARS.charAt(randomInt));
        }
        randomURL.append(POSTFIX);
        return randomURL.toString();
    }

    public String generateRandomDate() {
        int month = rng.nextInt(12) + 1;
        int day = rng.nextInt(31) + 1;
        int year = rng.nextInt(3000);

        StringBuffer randomDate = new StringBuffer();
        randomDate.append(month);
        randomDate.append("/");
        randomDate.append(day);
        randomDate.append("/");
        randomDate.append(year);
        return randomDate.toString();
    }

    public String generateRandomIP() {
        StringBuffer randomIP = new StringBuffer();
        for (int x = 0; x < 3; x++) {
            randomIP.append(rng.nextInt(255));
            randomIP.append(".");
        }
        randomIP.append(rng.nextInt(255));
        return randomIP.toString();
    }
}
