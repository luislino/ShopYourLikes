package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Link {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long linkID;
    private Long publisherID;
    private Long merchantID;
    private Long redirects;
    private Long earnings;

    private String customTitle;
    private String originalURL;
    private String imageRedirectPermahashLink;

    protected Link() {}

    public Link(Long publisherID, Long merchantID, Long redirects, Long earnings, String customTitle, String originalURL,
                String imageRedirectPermahashLink) {
        this.publisherID = publisherID;
        this.merchantID = merchantID;
        this.redirects = redirects;
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

    public Long getRedirects() {
        return redirects;
    }

    public void setRedirects(Long redirects) {
        this.redirects = redirects;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
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