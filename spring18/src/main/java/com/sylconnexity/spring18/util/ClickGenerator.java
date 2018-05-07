package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Click;
import com.sylconnexity.spring18.dbschema.ClickRepository;

public class ClickGenerator extends RandomGenerator {
    private ClickRepository clickRepo;

    public ClickGenerator(ClickRepository clickRepo) {
        this.clickRepo = clickRepo;
    }

    public void generateClick(Long linkID) {
        Double orderAmount;
        String orderNumber;
        Long unitsOrdered;
        Boolean convertedToSale = generateRandomBoolean();
        if (convertedToSale) {
            orderAmount = generateRandomDouble();
            orderNumber = generateRandomString(6);
            unitsOrdered = generateRandomLong();
        }
        else {
            orderAmount = 0.0;
           orderNumber = null;
           unitsOrdered = (long) 0;
        }

        String randomDate = generateRandomDate();
        String randomIP = generateRandomIP();
        String dma = generateRandomString(30);
        clickRepo.save(new Click(linkID, orderAmount, orderNumber, unitsOrdered, convertedToSale, randomDate, randomIP, dma));
    }
}
