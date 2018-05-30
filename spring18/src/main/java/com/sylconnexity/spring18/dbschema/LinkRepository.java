package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for creating Link queries.
 */
public interface LinkRepository extends CrudRepository<Link, Long> {

    /**
     * Find links with the given publisher ID.
     *
     * @param publisherID The ID of a publisher
     * @return A list of Links with the given publisherID
     */
    List<Link> findByPublisherID(Long publisherID);

    /**
     * Find links with the given merchant ID
     *
     * @param merchantID The ID of a merchant
     * @return A list of Links with the given merchantID
     */
    List<Link> findByMerchantID(Long merchantID);

    /**
     * Find links with the given publisher ID and the given merchant ID.
     *
     * @param publisherID The ID of a publisher
     * @param merchantID  The ID of a merchant
     * @return A list of Links with the given associated IDs
     */
    List<Link> findByPublisherIDAndMerchantID(Long publisherID, Long merchantID);

    /**
     * Find links with the given publisher ID and the given group name.
     *
     * @param publisherID The ID of a publisher
     * @param group       The name of a group
     * @return A list of Links with the given associated data
     */
    List<Link> findByPublisherIDAndGroup(Long publisherID, String group);
}
