package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Click {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long clickID;       //Identifier for a given click

    private Long linkID;

    private Double orderAmount; //If the click converted to a sale this field will hold the total order amount in dollars
    private String orderNumber; //If the click converted to a sale this field will hold the order number associated with the order
    private Long unitsOrdered;  //If the click converted to a sale this field will hold the total number of items purchased with that order

    private Boolean convertedToSale; //True if click converted to a sale and False otherwise

    private String redirectDate; //The date at which a click happened
    private String ipAddress;    //The IP address of the user who clicked on a link
    private String dma;          //The designated market area (http://www.thevab.com/wp-content/uploads/2016/06/2015-2016-TV-DMA-map.pdf)

    protected Click() {}

    public Click(Long linkID, Double orderAmount, String orderNumber, Long unitsOrdered,
                 Boolean convertedToSale, String redirectDate, String ipAddress, String dma) {
        this.linkID = linkID;
        this.orderAmount = orderAmount;
        this.orderNumber = orderNumber;
        this.unitsOrdered = unitsOrdered;
        this.convertedToSale = convertedToSale;
        this.redirectDate = redirectDate;
        this.ipAddress = ipAddress;
        this.dma = dma;
    }

    public Long getClickID() {
        return clickID;
    }

    public void setClickID(Long clickID) {
        this.clickID = clickID;
    }

    public Long getLinkID() {
        return linkID;
    }

    public void setLinkID(Long linkID) {
        this.linkID = linkID;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getUnitsOrdered() {
        return unitsOrdered;
    }

    public void setUnitsOrdered(Long unitsOrdered) {
        this.unitsOrdered = unitsOrdered;
    }

    public Boolean getConvertedToSale() {
        return convertedToSale;
    }

    public void setConvertedToSale(Boolean convertedToSale) {
        this.convertedToSale = convertedToSale;
    }

    public String getRedirectDate() {
        return redirectDate;
    }

    public void setRedirectDate(String redirectDate) {
        this.redirectDate = redirectDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDma() {
        return dma;
    }

    public void setDma(String dma) {
        this.dma = dma;
    }
}
