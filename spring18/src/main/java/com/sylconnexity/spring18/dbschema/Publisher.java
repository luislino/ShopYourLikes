package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A class defining the schema for Publishers. Publishers are uniquely identified by an automatically generated publisherID.
 * <br><br>
 * Publishers also have an associated username and apiKey.
 */
@Entity // This tells Hibernate to make a table out of this class
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherID; //Identifier for an individual influencer

    private String username;  //Username associated with the influencer
    private String apiKey;    //Internal key utilized by ShopYourLikes API to authenticate access

    protected Publisher() {
    }

    /**
     * Constructs a Publisher with an automatically generated, unique publisherID.
     *
     * @param username Username associated with the influencer
     * @param apiKey   Internal key utilized by ShopYourLikes API to authenticate access
     */
    public Publisher(String username, String apiKey) {
        this.username = username;
        this.apiKey = apiKey;
    }

    public Long getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(Long publisherID) {
        this.publisherID = publisherID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}