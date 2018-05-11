package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    List<Merchant> findByMerchantName(String MerchantName);
}
