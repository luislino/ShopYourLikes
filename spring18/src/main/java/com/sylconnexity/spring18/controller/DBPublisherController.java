package com.sylconnexity.spring18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sylconnexity.spring18.dbschema.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for the Publisher class, the API of which is available at /publishers.
 */
@Controller
@RequestMapping(path = "/publishers")
public class DBPublisherController {
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private LinkRepository linkRepository;

    /**
     * Gets Publishers, filtering them by the given username if it is provided.
     *
     * @param username A username of Publishers
     * @return A list of publishers, filtered by the provided username if given
     */
    @Cacheable("Publishers")
    @GetMapping(path = "")
    public @ResponseBody
    Iterable<Publisher> getPublishers(@RequestParam(value = "username", defaultValue = "") String username) {
        if (!username.equals(""))
            return publisherRepository.findByUsername(username);
        return publisherRepository.findAll();
    }

    /**
     * Returns the Publisher with the given ID if it exists.
     *
     * @param publisherID The ID of a publisher
     * @return The Publisher with the given ID, or null if it does not exist
     */
    @Cacheable("Publisher")
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Publisher getPublisherByID(@PathVariable(value = "id") Long publisherID) {
        Optional<Publisher> res = publisherRepository.findById(publisherID);
        if (!res.isPresent())
            return null;
        return res.get();
    }

    /**
     * Creates a new Publisher with the provided information.
     *
     * @param username Username associated with the influencer
     * @param apiKey   Internal key utilized by ShopYourLikes API to authenticate access
     * @return The newly created Publisher
     */
    @CacheEvict(cacheNames = {"Publisher", "Publishers"}, allEntries = true)
    @PostMapping(path = "")
    public @ResponseBody
    Publisher savePublisher(@RequestParam(value = "username") String username,
                            @RequestParam(value = "apiKey") String apiKey) {
        Publisher created = new Publisher(username, apiKey);
        return publisherRepository.save(created);
    }

    /**
     * Updates the username and API Key of the Publisher with the given ID if provided.
     *
     * @param publisherID The ID of a Publisher
     * @param username    Username associated with the influencer
     * @param apiKey      Internal key utilized by ShopYourLikes API to authenticate access
     * @return The updated Publisher, or null if the original did not exist
     */
    @CachePut(cacheNames = "Publisher", key = "#publisherID")
    @CacheEvict(cacheNames = "Publishers", allEntries = true)
    @PostMapping(path = "/{id}")
    public @ResponseBody
    Publisher savePublisherByID(@PathVariable(value = "id") Long publisherID,
                                @RequestParam(value = "username", defaultValue = "") String username,
                                @RequestParam(value = "apiKey", defaultValue = "") String apiKey) {
        if (!publisherRepository.existsById(publisherID)) {
            return null;
        }
        Publisher old = publisherRepository.findById(publisherID).get();
        if (!username.equals(""))
            old.setUsername(username);
        if (!apiKey.equals(""))
            old.setApiKey(apiKey);
        return publisherRepository.save(old);
    }

    /**
     * Deletes a Publisher with the given ID and all of its associated links and clicks.
     *
     * @param publisherID The ID of a publisher
     */
    @CacheEvict(cacheNames = {"Click", "Clicks", "Link", "Links", "Publisher", "Publishers"}, allEntries = true)
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    void deletePublisher(@PathVariable(value = "id") Long publisherID) {
        if (publisherRepository.existsById(publisherID))
            publisherRepository.deleteById(publisherID);
        List<Link> links = linkRepository.findByPublisherID(publisherID);
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