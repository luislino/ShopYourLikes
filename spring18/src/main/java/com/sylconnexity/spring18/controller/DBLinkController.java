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
 * Controller for the Link class, the API of which is available at /links.
 */
@Controller
@RequestMapping(path = "/links")
public class DBLinkController {
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    /**
     * Get the Links with the associated publisherID or merchantID if provided.
     *
     * @param publisherID The ID of an associated publisher
     * @param merchantID  The ID of an associated merchant
     * @return A list of links, filtered by the given IDs if provided
     */
    @Cacheable("Links")
    @GetMapping(path = "")
    public @ResponseBody
    Iterable<Link> getLinks(@RequestParam(value = "publisherID", defaultValue = "-1") Long publisherID,
                            @RequestParam(value = "merchantID", defaultValue = "-1") Long merchantID) {
        if (publisherID >= 0 && merchantID >= 0)
            return linkRepository.findByPublisherIDAndMerchantID(publisherID, merchantID);
        else if (publisherID >= 0)
            return linkRepository.findByPublisherID(publisherID);
        else if (merchantID >= 0)
            return linkRepository.findByMerchantID(merchantID);
        return linkRepository.findAll();
    }

    /**
     * Gets the Link with the given ID if it exists.
     *
     * @param linkID The ID of a link
     * @return The link with the given ID or null if it does not exist
     */
    @Cacheable("Link")
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Link getLinkByID(@PathVariable(value = "id") Long linkID) {
        Optional<Link> res = linkRepository.findById(linkID);
        if (!res.isPresent())
            return null;
        return res.get();
    }

    /**
     * Creates a new Link with the given information.
     *
     * @param publisherID                Identifier for an individual influencer
     * @param merchantID                 The merchant identifier for where the SYL Link landed
     * @param earnings                   Amount of money the influencer has earned from a SYL Link
     * @param customTitle                The title associated with the SYL Link that the user provided
     * @param originalURL                The original URL associated with the SYL Link that the user provided
     * @param imageRedirectPermahashLink The unique hash code associated with a SYL Link
     * @return The newly created Link
     */
    @CacheEvict(cacheNames = {"Link", "Links"}, allEntries = true)
    @PostMapping(path = "")
    public @ResponseBody
    Link saveLink(@RequestParam(value = "publisherID") Long publisherID,
                  @RequestParam(value = "merchantID") Long merchantID,
                  @RequestParam(value = "earnings") Double earnings,
                  @RequestParam(value = "customTitle", defaultValue = "New Link") String customTitle,
                  @RequestParam(value = "originalURL") String originalURL,
                  @RequestParam(value = "imageRedirectPermahashLink") String imageRedirectPermahashLink,
                  @RequestParam(value = "group") String group) {
        if (!publisherRepository.existsById(publisherID) || !merchantRepository.existsById(merchantID)) {
            return null;
        }
        Link created = new Link(publisherID, merchantID, earnings, customTitle, originalURL, imageRedirectPermahashLink, group);
        return linkRepository.save(created);
    }

    /**
     * Updates the Link with the given ID if it exists with the provided information.
     *
     * @param linkID      The ID of a Link
     * @param earnings    The new earnings of the link
     * @param customTitle The new title of the link
     * @return The updated Link, or null if the original ID did not exist
     */
    @CachePut(cacheNames = "Link", key = "#linkID")
    @CacheEvict(cacheNames = "Links", allEntries = true)
    @PostMapping(path = "/{id}")
    public @ResponseBody
    Link saveLinkByID(@PathVariable(value = "id") Long linkID,
                      @RequestParam(value = "earnings", defaultValue = "-1.0") Double earnings,
                      @RequestParam(value = "customTitle", defaultValue = "") String customTitle,
                      @RequestParam(value = "group", defaultValue = "") String group) {
        if (!linkRepository.existsById(linkID)) {
            return null;
        }
        Link old = linkRepository.findById(linkID).get();
        if (earnings > 0)
            old.setEarnings(earnings);
        if (!customTitle.equals(""))
            old.setCustomTitle(customTitle);
        if (!group.equals(""))
            old.setGroup(group);
        return linkRepository.save(old);
    }

    /**
     * Deletes the Link with the given ID and its associated clicks.
     *
     * @param linkID The ID of a Link
     */
    @CacheEvict(cacheNames = {"Click", "Clicks", "Link", "Links"}, allEntries = true)
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    void deleteLink(@PathVariable(value = "id") Long linkID) {
        if (linkRepository.existsById(linkID))
            linkRepository.deleteById(linkID);
        List<Click> clicks = clickRepository.findByLinkID(linkID);
        if (clicks != null)
            clickRepository.deleteAll(clicks);
    }
}
