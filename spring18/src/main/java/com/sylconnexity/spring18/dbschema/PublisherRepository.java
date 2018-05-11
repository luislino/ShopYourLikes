package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Publisher queries.
 */
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    /**
     * Find Publishers with the given username.
     * @param username The username of a publisher
     * @return A list of publishers with the given username
     */
    List<Publisher> findByUsername(String username);
}
