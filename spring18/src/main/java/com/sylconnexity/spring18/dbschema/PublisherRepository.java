package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Publisher queries.
 */
public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
