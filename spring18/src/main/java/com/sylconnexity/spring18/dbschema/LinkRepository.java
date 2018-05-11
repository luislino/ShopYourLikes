package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Link queries.
 */
public interface LinkRepository extends CrudRepository<Link, Long> {

    /**
     * Find links with the given publisher ID.
     * @param publisherID The ID of a publisher
     * @return A list of Links with the given publisherID
     */
    List<Link> findByPublisherID(Long publisherID);
}