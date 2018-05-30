package com.sylconnexity.spring18.util;

import java.util.Random;
import java.lang.StringBuffer;

/**
 * A class that randomly generates primitives such as longs and doubles. It can also generate formatted strings such as IP addresses.
 */
public class RandomGenerator {
    private final String CHARS = "0123456789abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
    private Random rng;

    /**
     * Constructs a RandomGenerator based on a unique seed.
     */
    public RandomGenerator() {
        rng = new Random();
    }

    /**
     * Generates a random long.
     *
     * @return A random long
     */
    public Long generateRandomLong() {
        return rng.nextLong();
    }

    /**
     * Generates a random double.
     *
     * @return A random double
     */
    public Double generateRandomDouble() {
        return rng.nextDouble();
    }

    /**
     * Generates a random boolean.
     *
     * @return A random boolean
     */
    public Boolean generateRandomBoolean() {
        int randomInt = rng.nextInt(2);
        if (randomInt == 0)
            return false;
        else
            return true;
    }

    /**
     * Generates a random string of alphanumeric characters.
     *
     * @param size The size of the generated string
     * @return An alphanumeric string of the given size
     */
    public String generateRandomString(int size) {
        StringBuffer randomString = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
            int randomInt = rng.nextInt(CHARS.length());
            randomString.append(CHARS.charAt(randomInt));
        }
        return randomString.toString();
    }

    /**
     * Generates a random URL prefixed by "https://www." and postfixed by ".com/".
     * In between the prefix and postfix are alphanumeric characters.
     *
     * @param size The size of the alphanumeric characters between the prefix and postfix
     * @return A randomly generated URL with a central portion of the given size
     */
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

    /**
     * Generates a random date in the format MM/DD/YYYY with the years bounded by 3000 (exclusively).
     *
     * @return A random date in the format MM/DD/YYYY
     */
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

    /**
     * Generates a random IPv4 address ranging from 0.0.0.0 to 255.255.255.255
     *
     * @return A random IPv4 address
     */
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
