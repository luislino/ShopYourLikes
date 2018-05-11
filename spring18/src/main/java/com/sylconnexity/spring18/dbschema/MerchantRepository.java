package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Merchant queries.
 */
public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    /**
     * Find Merchants with the given name.
     * @param merchantName The name of a merchant
     * @return A list of merchants with the given merchant name
     */
    List<Merchant> findByMerchantName(String merchantName);
}
