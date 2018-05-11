package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Merchant;
import com.sylconnexity.spring18.dbschema.MerchantRepository;

/**
 * A RandomGenerator that generates random Merchants.
 */
public class MerchantGenerator extends RandomGenerator {
    private MerchantRepository merchantRepo;

    /**
     * Constructs a MerchantGenerator with a given repository.
     * @param merchantRepo A merchant repository
     */
    public MerchantGenerator(MerchantRepository merchantRepo) {
        this.merchantRepo = merchantRepo;
    }

    /**
     * Generates a random merchant with a name of 20 characters.
     */
    public void generateMerchant() {
        String merchantName = generateRandomString(20);
        merchantRepo.save(new Merchant(merchantName));
    }
}
