package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Publisher;
import com.sylconnexity.spring18.dbschema.PublisherRepository;

public class PublisherGenerator extends RandomGenerator {
    private PublisherRepository publisherRepo;

    public PublisherGenerator(PublisherRepository publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    public void generatePublisher() {
        String username = generateRandomString(20);
        String apiKey = generateRandomString(19);
        publisherRepo.save(new Publisher(username, apiKey));
    }

    public void generatePublisher(Long publisherID) {
        String username = generateRandomString(20);
        String apiKey = generateRandomString(19);
        publisherRepo.save(new Publisher(publisherID, username, apiKey));
    }
}
