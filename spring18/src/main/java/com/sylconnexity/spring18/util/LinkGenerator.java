package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Link;
import com.sylconnexity.spring18.dbschema.LinkRepository;

public class LinkGenerator extends RandomGenerator {
    private LinkRepository linkRepo;

    public LinkGenerator(LinkRepository linkRepo) {
        this.linkRepo = linkRepo;
    }

    public void generateLink(Long publisherID, Long merchantID) {
        Double earnings = generateRandomDouble();
        String customTitle = generateRandomString(10);
        String originalURL = generateRandomURL(10);
        String imageRedirectPermahashLink = generateRandomString(40);
        linkRepo.save(new Link(publisherID, merchantID, earnings, customTitle,
                originalURL, imageRedirectPermahashLink));
    }
}
