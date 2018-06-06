package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Click;
import com.sylconnexity.spring18.dbschema.ClickRepository;

/**
 * A RandomGenerator that generates random clicks.
 */
public class ClickGenerator extends RandomGenerator {
    private ClickRepository clickRepo;

    /**
     * Constructs a ClickGenerator with a given repository.
     *
     * @param clickRepo A click repository
     */
    public ClickGenerator(ClickRepository clickRepo) {
        this.clickRepo = clickRepo;
    }

    /**
     * Randomly generates a click associated with the given link ID. This click has a randomly generated
     * order cost, order ID, and number of units ordered if it was converted to a sale (true or false, randomly).
     * Otherwise, these values are set to 0 or null, depending on their types.
     * <br><br>
     * This Click also has a randomly generated DMA of 30 characters, a random date, and a random IP.
     *
     * @param linkID The ID of the associated link.
     */
    public void generateClick(Long linkID) {
        Double orderAmount;
        String orderNumber;
        Long unitsOrdered;
        Boolean convertedToSale = generateRandomBoolean();
        if (convertedToSale) {
            orderAmount = generateRandomDouble();
            orderNumber = generateRandomString(6);
            unitsOrdered = generateRandomLong();
        } else {
            orderAmount = 0.0;
            orderNumber = null;
            unitsOrdered = 0L;
        }

        String randomDate = generateRandomDate();
        String randomIP = generateRandomIP();
        String dma = generateRandomString(30);
        clickRepo.save(new Click(linkID, orderAmount, orderNumber, unitsOrdered, convertedToSale, randomDate, randomIP, dma));
    }
}
