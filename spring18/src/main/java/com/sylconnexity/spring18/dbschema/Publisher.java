package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Publisher {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long publisherID; //Identifier for an individual influencer

    private String username;  //Username associated with the influencer
    private String apiKey;    //Internal key utilized by ShopYourLikes API to authenticate access

    protected Publisher() {}

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