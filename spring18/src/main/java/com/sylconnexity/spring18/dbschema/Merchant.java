package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A class defining the schema for Merchants. Merchants are uniquely identified by an automatically generated merchantID.
 * <br><br>
 * Merchants also have an associated merchantName.
 */
@Entity // This tells Hibernate to make a table out of this class
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long merchantID;     //The merchant identifier for where the SYL Link landed

    private String merchantName; //The merchant name associated with where the SYL Link landed

    protected Merchant() {
    }

    /**
     * Constructs a Merchant with an automatically generated, unique merchantID.
     *
     * @param merchantName The merchant name associated with where the SYL Link landed
     */
    public Merchant(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(Long merchantID) {
        this.merchantID = merchantID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}