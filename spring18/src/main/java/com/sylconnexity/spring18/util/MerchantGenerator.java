package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Merchant;
import com.sylconnexity.spring18.dbschema.MerchantRepository;

public class MerchantGenerator extends RandomGenerator {
    private MerchantRepository merchantRepo;

    public MerchantGenerator(MerchantRepository merchantRepo) {
        this.merchantRepo = merchantRepo;
    }

    public void generateMerchant() {
        String merchantName = generateRandomString(20);
        merchantRepo.save(new Merchant(merchantName));
    }

    public void generateMerchant(Long merchantID) {
        String merchantName = generateRandomString(20);
        merchantRepo.save(new Merchant(merchantID, merchantName));
    }
}
