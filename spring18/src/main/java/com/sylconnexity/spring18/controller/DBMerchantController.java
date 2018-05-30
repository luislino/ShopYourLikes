package com.sylconnexity.spring18.controller;

import com.sylconnexity.spring18.dbschema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for the Merchant class, the API of which is available at /merchants.
 */
@Controller
@RequestMapping(path = "/merchants")
public class DBMerchantController {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private LinkRepository linkRepository;

    /**
     * Gets the merchants with the associated name if provided.
     *
     * @param merchantName The name of a merchant
     * @return A list of merchants, with the given name if provided
     */
    @Cacheable("Merchants")
    @GetMapping(path = "")
    public @ResponseBody
    Iterable<Merchant> getMerchants(@RequestParam(value = "merchantName", defaultValue = "") String merchantName) {
        if (!merchantName.equals(""))
            return merchantRepository.findByMerchantName(merchantName);
        return merchantRepository.findAll();
    }

    /**
     * Get the Merchant with the given ID if it exists.
     *
     * @param merchantID The ID of a merchant
     * @return The Merchant with the given ID, or null if it does not exist
     */
    @Cacheable("Merchant")
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Merchant getMerchantByID(@PathVariable(value = "id") Long merchantID) {
        Optional<Merchant> res = merchantRepository.findById(merchantID);
        if (!res.isPresent())
            return null;
        return res.get();
    }

    /**
     * Creates a new merchant with the given merchant name.
     *
     * @param merchantName The merchant name associated with where the SYL Link landed
     * @return The newly created merchant
     */
    @CacheEvict(cacheNames = {"Merchant", "Merchants"}, allEntries = true)
    @PostMapping(path = "")
    public @ResponseBody
    Merchant saveMerchant(@RequestParam(value = "merchantName") String merchantName) {
        Merchant created = new Merchant(merchantName);
        return merchantRepository.save(created);
    }

    /**
     * Updates a merchant of the given ID with the given merchant name if it exists.
     *
     * @param merchantID   The ID of a merchant
     * @param merchantName The new name of a merchant
     * @return The updated merchant, or null if the original did not exist
     */
    @CachePut(cacheNames = "Merchant", key = "#merchantID")
    @CacheEvict(cacheNames = "Merchants", allEntries = true)
    @PostMapping(path = "/{id}")
    public @ResponseBody
    Merchant saveMerchantByID(@PathVariable(value = "id") Long merchantID,
                              @RequestParam(value = "merchantName") String merchantName) {
        if (!merchantRepository.existsById(merchantID)) {
            return null;
        }
        Merchant old = merchantRepository.findById(merchantID).get();
        old.setMerchantName(merchantName);
        return merchantRepository.save(old);
    }

    /**
     * Deletes a merchant and all of its associated clicks and links.
     *
     * @param merchantID The ID of an associated merchant
     */
    @CacheEvict(cacheNames = {"Click", "Clicks", "Link", "Links", "Merchant", "Merchants"}, allEntries = true)
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    void deleteMerchant(@PathVariable(value = "id") Long merchantID) {
        if (merchantRepository.existsById(merchantID))
            merchantRepository.deleteById(merchantID);
        List<Link> links = linkRepository.findByMerchantID(merchantID);
        if (links == null)
            return;

        for (Link l : links) {
            List<Click> clicks = clickRepository.findByLinkID(l.getLinkID());
            if (clicks != null)
                clickRepository.deleteAll(clicks);
        }
        linkRepository.deleteAll(links);
    }
}
