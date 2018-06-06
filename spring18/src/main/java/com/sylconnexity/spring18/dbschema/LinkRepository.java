package com.sylconnexity.spring18.dbschema;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * A repository for creating Link queries.
 */
public interface LinkRepository extends CrudRepository<Link, Long> {

    /**
     * Find links with the given, publisher ID, merchant ID, or group name. If the IDs are
     * -1 or the group name is the empty string, they are ignored.
     *
     * @param publisherID The ID of a publisher
     * @param merchantID The ID of a merchant
     * @param groupName The name of a link's group
     * @return A list of Links matching the given criteria.
     */
    @Query("SELECT link FROM Link link WHERE " +
            "(:publisherID = -1L OR link.publisherID = :publisherID)" +
            "AND (:merchantID = -1L OR link.merchantID = :merchantID) " +
            "AND (:groupName = '' OR link.groupName = :groupName)")
    List<Link> findByIDsAndGroup(@Param("publisherID") Long publisherID,
                                 @Param("merchantID") Long merchantID,
                                 @Param("groupName") String groupName);

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
}
