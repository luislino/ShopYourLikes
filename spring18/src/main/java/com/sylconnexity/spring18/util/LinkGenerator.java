package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Link;
import com.sylconnexity.spring18.dbschema.LinkRepository;

/**
 * A RandomGenerator that generates random Links.
 */
public class LinkGenerator extends RandomGenerator {
    private LinkRepository linkRepo;

    /**
     * Constructs a LinkGenerator with a given repository.
     *
     * @param linkRepo A link repository
     */
    public LinkGenerator(LinkRepository linkRepo) {
        this.linkRepo = linkRepo;
    }

    /**
     * Randomly generates a link with a given publisher and merchant ID. This link will have
     * a title of 10 characters, an original URL of 10 characters, and a hash of 40 characters.
     *
     * @param publisherID The ID of the associated publisher
     * @param merchantID  The ID of the associated merchant
     */
    public void generateLink(Long publisherID, Long merchantID) {
        Double earnings = generateRandomDouble();
        String customTitle = generateRandomString(10);
        String originalURL = generateRandomURL(10);
        String imageRedirectPermahashLink = generateRandomString(40);
        String group = generateRandomString(10);
        linkRepo.save(new Link(publisherID, merchantID, earnings, customTitle,
                originalURL, imageRedirectPermahashLink, group));
    }
}
