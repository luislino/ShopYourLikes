package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Click queries.
 */
public interface ClickRepository extends CrudRepository<Click, Long> {

}