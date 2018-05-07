package com.sylconnexity.spring18.dbschema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Merchant {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long merchantID;     //The merchant identifier for where the SYL Link landed

    private String merchantName; //The merchant name associated with where the SYL Link landed

    protected Merchant() {}

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