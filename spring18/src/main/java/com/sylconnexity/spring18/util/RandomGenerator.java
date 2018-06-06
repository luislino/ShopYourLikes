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
     * Generates a random dma code
     *
     * @
     * @return An string with a randomly selected dma number
     */
    public String generateRandomDma() {

        int[] dma_codes = new int[]{657,
                510,
                525,
                644,
                583,
                574,
                634,
                679,
                743,
                567,
                658,
                524,
                500,
                520,
                611,
                635,
                800,
                512,
                537,
                716,
                563,
                513,
                821,
                756,
                502,
                630,
                675,
                757,
                736,
                754,
                625,
                514,
                540,
                510,
                648,
                519,
                517,
                584,
                575,
                602,
                515,
                531,
                546,
                522,
                535,
                600,
                542,
                691,
                648,
                751,
                505,
                687,
                606,
                560,
                702,
                771,
                628,
                765,
                588,
                565,
                516,
                801,
                802,
                649,
                745,
                560,
                691,
                528,
                548,
                686,
                509,
                623,
                592,
                798,
                755,
                647,
                746,
                511,
                569,
                533,
                766,
                543,
                744,
                618,
                564,
                678,
                527,
                637,
                639,
                718,
                561,
                604,
                734,
                747,
                616,
                722,
                810,
                717,
                631,
                813,
                557,
                582,
                642,
                643,
                551,
                749,
                839,
                710,
                627,
                541,
                558,
                709,
                803,
                529,
                651,
                709,
                573,
                503,
                669,
                506,
                737,
                553,
                636,
                534,
                640,
                711,
                633,
                617,
                762,
                725,
                862,
                682,
                698,
                773,
                632,
                570,
                709,
                571,
                659,
                521,
                533,
                622,
                501,
                544,
                740,
                559,
                650,
                652,
                804,
                656,
                597,
                686,
                686,
                556,
                504,
                753,
                693,
                603,
                508,
                523,
                758,
                692,
                820,
                552,
                752,
                764,
                868,
                811,
                705,
                767,
                538,
                610,
                670,
                828,
                576,
                770,
                661,
                641,
                825,
                807,
                855,
                790,
                539,
                507,
                759,
                577,
                698,
                612,
                789,
                624,
                725,
                881,
                619,
                648,
                638,
                609,
                613,
                539,
                554,
                676,
                662,
                555,
                819,
                581,
                530,
                547,
                605,
                532,
                789,
                671,
                760,
                526,
                724,
                626,
                866,
                511,
                545,
                549,
                673,
                598,
                627,
                687,
                550,
                518,
                566,
                536,
                596};
        Random rand = new Random();
        int n = rand.nextInt(227);
        return String.valueOf(dma_codes[n]);

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
