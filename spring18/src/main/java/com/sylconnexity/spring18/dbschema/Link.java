package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Link {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long linkID;      //Identifier for a given link

    private Long publisherID; //Identifier for an individual influencer
    private Long merchantID;  //The merchant identifier for where the SYL Link landed

    private Double earnings;    //Amount of money the influencer has earned from a SYL Link

    private String customTitle;                 //The title associated with the SYL Link that the user provided
    private String originalURL;                 //The original URL associated with the SYL Link that the user provided
    private String imageRedirectPermahashLink;  //The unique hash code associated with a SYL Link

    protected Link() {}

    public Link(Long publisherID, Long merchantID, Double earnings, String customTitle, String originalURL,
                String imageRedirectPermahashLink) {
        this.publisherID = publisherID;
        this.merchantID = merchantID;
        this.earnings = earnings;
        this.customTitle = customTitle;
        this.originalURL = originalURL;
        this.imageRedirectPermahashLink = imageRedirectPermahashLink;
    }

    public Long getLinkID() {
        return linkID;
    }

    public void setLinkID(Long linkID) {
        this.linkID = linkID;
    }

    public Long getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(Long publisherID) {
        this.publisherID = publisherID;
    }

    public Long getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(Long merchantID) {
        this.merchantID = merchantID;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getImageRedirectPermahashLink() {
        return imageRedirectPermahashLink;
    }

    public void setImageRedirectPermahashLink(String imageRedirectPermahashLink) {
        this.imageRedirectPermahashLink = imageRedirectPermahashLink;
    }

}