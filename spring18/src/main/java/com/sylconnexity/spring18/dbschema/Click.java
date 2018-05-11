package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A class defining the schema for Clicks. Clicks are uniquely identified by an automatically generated clickID
 * and associated with a link identified by a linkID. If a click was convertedToSale, then it has values in
 * orderAmount, orderNumber, and unitsOrdered, specifying the total order amount in dollars, the order ID
 * associated with the order, and the total number of items purchased with that order.
 * <br><br>
 * Clicks also have a redirectDate for the date at which a click happened, an ipAddress of the user associated with that click, and
 * a dma, specifying the <a href="https://en.wikipedia.org/wiki/List_of_United_States_television_markets">designated market area</a>
 * of that click.
 * <br><br>
 * All number values are stored as Longs.
 */
@Entity // This tells Hibernate to make a table out of this class
public class Click {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long clickID;       //Identifier for a given click

    private Long linkID;        //Identifier for the associated link of the click

    private Double orderAmount; //If the click converted to a sale this field will hold the total order amount in dollars
    private String orderNumber; //If the click converted to a sale this field will hold the order number associated with the order
    private Long unitsOrdered;  //If the click converted to a sale this field will hold the total number of items purchased with that order

    private Boolean convertedToSale; //True if click converted to a sale and False otherwise

    private String redirectDate; //The date at which a click happened
    private String ipAddress;    //The IP address of the user who clicked on a link
    private String dma;          //The designated market area (http://www.thevab.com/wp-content/uploads/2016/06/2015-2016-TV-DMA-map.pdf)

    protected Click() {}

    /**
     * Constructs a Click with an automatically generated, unique clickID.
     * @param linkID Identifier for the associated link of the click
     * @param orderAmount If the click converted to a sale this field will hold the total order amount in dollars
     * @param orderNumber If the click converted to a sale this field will hold the total number of items purchased with that order
     * @param unitsOrdered If the click converted to a sale this field will hold the total number of items purchased with that order
     * @param convertedToSale True if click converted to a sale and False otherwise
     * @param redirectDate The date at which a click happened
     * @param ipAddress The IP address of the user who clicked on a link
     * @param dma The designated market area
     */
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
