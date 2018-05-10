package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Merchant queries.
 */
public interface MerchantRepository extends CrudRepository<Merchant, Long> {

}
