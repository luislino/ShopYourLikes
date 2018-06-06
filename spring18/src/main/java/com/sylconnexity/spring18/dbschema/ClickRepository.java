package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Click queries.
 */
public interface ClickRepository extends CrudRepository<Click, Long> {

    /**
     * Find clicks with the given link ID.
     *
     * @param linkID The ID of a link
     * @return A list of Clicks with the given linkID
     */
    List<Click> findByLinkID(Long linkID);

}