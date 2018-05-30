package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A class defining the schema for Links. Links are uniquely identified by an automatically generated linkID
 * and associated with the publisherID of a given publisher and the merchantID of a given merchant.
 * <br><br>
 * Links also have an associated earnings, the amount of money earned from the link. They have a customTitle provided
 * by the user; an originalURL of the link before it was converted to a SYL link; and a imageRedirectPermahashLink, the
 * unique hash code associated with the link.
 */
@Entity // This tells Hibernate to make a table out of this class
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkID;      //Identifier for a given link

    private Long publisherID; //Identifier for an individual influencer
    private Long merchantID;  //The merchant identifier for where the SYL Link landed

    private Double earnings;    //Amount of money the influencer has earned from a SYL Link

    private String customTitle;                 //The title associated with the SYL Link that the user provided
    private String originalURL;                 //The original URL associated with the SYL Link that the user provided
    private String imageRedirectPermahashLink;  //The unique hash code associated with a SYL Link

    private String group;                       //The name of the group that the link belongs to

    protected Link() {
    }

    /**
     * Constructs a Link with an automatically generated, unique linkID.
     *
     * @param publisherID                Identifier for an individual influencer
     * @param merchantID                 The merchant identifier for where the SYL Link landed
     * @param earnings                   Amount of money the influencer has earned from a SYL Link
     * @param customTitle                The title associated with the SYL Link that the user provided
     * @param originalURL                The original URL associated with the SYL Link that the user provided
     * @param imageRedirectPermahashLink The unique hash code associated with a SYL Link
     * @param group                      The name of the group that the link belongs to
     */
    public Link(Long publisherID, Long merchantID, Double earnings, String customTitle, String originalURL,
                String imageRedirectPermahashLink, String group) {
        this.publisherID = publisherID;
        this.merchantID = merchantID;
        this.earnings = earnings;
        this.customTitle = customTitle;
        this.originalURL = originalURL;
        this.imageRedirectPermahashLink = imageRedirectPermahashLink;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}