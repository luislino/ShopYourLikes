package com.sylconnexity.spring18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sylconnexity.spring18.dbschema.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for the Click class, the API of which is available at /clicks.
 */
@Controller
@RequestMapping(path="/clicks")
class ClickController {
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private LinkRepository linkRepository;

    /**
     * Get all Clicks or Clicks with a given link ID.
     * @param linkID The ID of an associated link
     * @return A list of Clicks, with the given link ID if provided
     */
    @GetMapping(path="")
    public @ResponseBody Iterable<Click> getClicks(@RequestParam(value="linkID", defaultValue="-1") Long linkID) {
        if (linkID >= 0)
            return clickRepository.findByLinkID(linkID);
        return clickRepository.findAll();
    }

    /**
     * Get the Click with the given ID.
     * @param id The ID of a click
     * @return The click with the given ID or null if it does not exist
     */
    @GetMapping(path="/{id}")
    public @ResponseBody Click getClickByID(@PathVariable(value="id") Long id) {
        Optional<Click> res = clickRepository.findById(id);
        if (!res.isPresent())
            return null;
        return res.get();
    }

    /**
     * Creates a new click with the provided information.
     * @param linkID Identifier for the associated link of the click
     * @param orderAmount If the click converted to a sale this field will hold the total order amount in dollars
     * @param orderNumber If the click converted to a sale this field will hold the total number of items purchased with that order
     * @param unitsOrdered If the click converted to a sale this field will hold the total number of items purchased with that order
     * @param convertedToSale True if click converted to a sale and False otherwise
     * @param redirectDate The date at which a click happened
     * @param ipAddress The IP address of the user who clicked on a link
     * @param dma The designated market area
     * @return The newly created click
     */
    @PostMapping(path="")
    public @ResponseBody Click saveClick(@RequestParam(value="linkID") Long linkID,
                                         @RequestParam(value="orderAmount") Double orderAmount,
                                         @RequestParam(value="orderNumber") String orderNumber,
                                         @RequestParam(value="unitsOrdered") Long unitsOrdered,
                                         @RequestParam(value="convertedToSale") Boolean convertedToSale,
                                         @RequestParam(value="redirectDate") String redirectDate,
                                         @RequestParam(value="ipAddress") String ipAddress,
                                         @RequestParam(value="dma") String dma) {
        if (!linkRepository.existsById(linkID)) {
            return null;
        }
        Click created = new Click(linkID, orderAmount, orderNumber, unitsOrdered, convertedToSale, redirectDate, ipAddress, dma);
        return clickRepository.save(created);
    }

    /**
     * Deletes the Click with the given ID if it exists.
     * @param id An ID of a Click
     */
    @DeleteMapping(path="/{id}")
    public @ResponseBody void deleteClick(@PathVariable(value="id") Long id) {
        if (clickRepository.existsById(id))
            clickRepository.deleteById(id);
    }
}

/**
 * Controller for the Link class, the API of which is available at /links.
 */
@Controller
@RequestMapping(path="/links")
class LinkController {
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
     * @param publisherID The ID of an associated publisher
     * @param merchantID The ID of an associated merchant
     * @return A list of links, filtered by the given IDs if provided
     */
    @GetMapping(path="")
    public @ResponseBody Iterable<Link> getLinks(@RequestParam(value="publisherID", defaultValue="-1") Long publisherID,
                                                 @RequestParam(value="merchantID", defaultValue="-1") Long merchantID) {
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
     * @param id The ID of a link
     * @return The link with the given ID or null if it does not exist
     */
    @GetMapping(path="/{id}")
    public @ResponseBody Link getLinkByID(@PathVariable(value="id") Long id) {
        Optional<Link> res = linkRepository.findById(id);
        if (!res.isPresent())
            return null;
        return res.get();
    }

    /**
     * Creates a new Link with the given information.
     * @param publisherID Identifier for an individual influencer
     * @param merchantID The merchant identifier for where the SYL Link landed
     * @param earnings Amount of money the influencer has earned from a SYL Link
     * @param customTitle The title associated with the SYL Link that the user provided
     * @param originalURL The original URL associated with the SYL Link that the user provided
     * @param imageRedirectPermahashLink The unique hash code associated with a SYL Link
     * @return The newly created Link
     */
    @PostMapping(path="")
    public @ResponseBody Link saveLink(@RequestParam(value="publisherID") Long publisherID,
                                           @RequestParam(value="merchantID") Long merchantID,
                                           @RequestParam(value="earnings") Double earnings,
                                           @RequestParam(value="customTitle", defaultValue="New Link") String customTitle,
                                           @RequestParam(value="originalURL") String originalURL,
                                           @RequestParam(value="imageRedirectPermahashLink") String imageRedirectPermahashLink) {
        if (!publisherRepository.existsById(publisherID) || !merchantRepository.existsById(merchantID)) {
            return null;
        }
        Link created = new Link(publisherID, merchantID, earnings, customTitle, originalURL, imageRedirectPermahashLink);
        return linkRepository.save(created);
    }

    /**
     * Updates the Link with the given ID if it exists with the provided information.
     * @param id The ID of a Link
     * @param earnings The new earnings of the link
     * @param customTitle The new title of the link
     * @return The updated Link, or null if the original ID did not exist
     */
    @PostMapping(path="/{id}")
    public @ResponseBody Link saveLinkByID(@PathVariable(value="id") Long id,
                                           @RequestParam(value="earnings", defaultValue="-1.0") Double earnings,
                                           @RequestParam(value="customTitle", defaultValue="") String customTitle) {
        if (!linkRepository.existsById(id)) {
            return null;
        }
        Link old = linkRepository.findById(id).get();
        if (earnings > 0)
            old.setEarnings(earnings);
        if (!customTitle.equals(""))
            old.setCustomTitle(customTitle);
        return linkRepository.save(old);
    }

    /**
     * Deletes the Link with the given ID and its associated clicks.
     * @param id The ID of a Link
     */
    @DeleteMapping(path="/{id}")
    public @ResponseBody void deleteLink(@PathVariable(value="id") Long id) {
        if (linkRepository.existsById(id))
            linkRepository.deleteById(id);
        List<Click> clicks = clickRepository.findByLinkID(id);
        if (clicks != null)
            clickRepository.deleteAll(clicks);
    }
}

/**
 * Controller for the Merchant class, the API of which is available at /merchants.
 */
@Controller
@RequestMapping(path="/merchants")
class MerchantController {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private LinkRepository linkRepository;

    /**
     * Gets the merchants with the associated name if provided.
     * @param merchantName The name of a merchant
     * @return A list of merchants, with the given name if provided
     */
    @GetMapping(path="")
    public @ResponseBody Iterable<Merchant> getMerchants(@RequestParam(value="merchantName", defaultValue="") String merchantName) {
        if (!merchantName.equals(""))
            return merchantRepository.findByMerchantName(merchantName);
        return merchantRepository.findAll();
    }

    /**
     * Get the Merchant with the given ID if it exists.
     * @param id The ID of a merchant
     * @return The Merchant with the given ID, or null if it does not exist
     */
    @GetMapping(path="/{id}")
    public @ResponseBody Merchant getMerchantByID(@PathVariable(value="id") Long id) {
        Optional<Merchant> res = merchantRepository.findById(id);
        if (!res.isPresent())
            return null;
        return res.get();
    }

    /**
     * Creates a new merchant with the given merchant name.
     * @param merchantName The merchant name associated with where the SYL Link landed
     * @return The newly created merchant
     */
    @PostMapping(path="")
    public @ResponseBody Merchant saveMerchant(@RequestParam(value="merchantName") String merchantName) {
        Merchant created = new Merchant(merchantName);
        return merchantRepository.save(created);
    }

    /**
     * Updates a merchant of the given ID with the given merchant name if it exists.
     * @param id The ID of a merchant
     * @param merchantName The new name of a merchant
     * @return The updated merchant, or null if the original did not exist
     */
    @PostMapping(path="/{id}")
    public @ResponseBody Merchant saveMerchantByID(@PathVariable(value="id") Long id,
                                                   @RequestParam(value="merchantName") String merchantName) {
        if (!merchantRepository.existsById(id)) {
            return null;
        }
        Merchant old = merchantRepository.findById(id).get();
        old.setMerchantName(merchantName);
        return merchantRepository.save(old);
    }

    /**
     * Deletes a merchant and all of its associated clicks and links.
     * @param id The ID of an associated merchant
     */
    @DeleteMapping(path="/{id}")
    public @ResponseBody void deleteMerchant(@PathVariable(value="id") Long id) {
        if (merchantRepository.existsById(id))
            merchantRepository.deleteById(id);
        List<Link> links = linkRepository.findByMerchantID(id);
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

/**
 * Controller for the Publisher class, the API of which is available at /publishers.
 */
@Controller
@RequestMapping(path="/publishers")
class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private LinkRepository linkRepository;

    /**
     * Gets Publishers, filtering them by the given username if it is provided.
     * @param username A username of Publishers
     * @return A list of publishers, filtered by the provided username if given
     */
    @GetMapping(path="")
    public @ResponseBody Iterable<Publisher> getPublishers(@RequestParam(value="username", defaultValue="") String username) {
        if (!username.equals(""))
            return publisherRepository.findByUsername(username);
        return publisherRepository.findAll();
    }

    /**
     * Returns the Publisher with the given ID if it exists.
     * @param id The ID of a publisher
     * @return The Publisher with the given ID, or null if it does not exist
     */
    @GetMapping(path="/{id}")
    public @ResponseBody Publisher getPublisherByID(@PathVariable(value="id") Long id) {
        Optional<Publisher> res = publisherRepository.findById(id);
        if (!res.isPresent())
            return null;
        return res.get();
    }

    /**
     * Creates a new Publisher with the provided information.
     * @param username Username associated with the influencer
     * @param apiKey Internal key utilized by ShopYourLikes API to authenticate access
     * @return The newly created Publisher
     */
    @PostMapping(path="")
    public @ResponseBody Publisher savePublisher(@RequestParam(value="username") String username,
                                                 @RequestParam(value="apiKey") String apiKey) {
        Publisher created = new Publisher(username, apiKey);
        return publisherRepository.save(created);
    }

    /**
     * Updates the username and API Key of the Publisher with the given ID if provided.
     * @param id The ID of a Publisher
     * @param username Username associated with the influencer
     * @param apiKey Internal key utilized by ShopYourLikes API to authenticate access
     * @return The updated Publisher, or null if the original did not exist
     */
    @PostMapping(path="/{id}")
    public @ResponseBody Publisher savePublisherByID(@PathVariable(value="id") Long id,
                                                     @RequestParam(value="username", defaultValue="") String username,
                                                     @RequestParam(value="apiKey", defaultValue="") String apiKey) {
        if (!publisherRepository.existsById(id)) {
            return null;
        }
        Publisher old = publisherRepository.findById(id).get();
        if (!username.equals(""))
            old.setUsername(username);
        if (!apiKey.equals(""))
            old.setApiKey(apiKey);
        return publisherRepository.save(old);
    }

    /**
     * Deletes a Publisher with the given ID and all of its associated links and clicks.
     * @param id The ID of a publisher
     */
    @DeleteMapping(path="/{id}")
    public @ResponseBody void deletePublisher(@PathVariable(value="id") Long id) {
        if (publisherRepository.existsById(id))
            publisherRepository.deleteById(id);
        List<Link> links = linkRepository.findByPublisherID(id);
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