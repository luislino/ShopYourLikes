package com.sylconnexity.spring18.controller;

import com.sylconnexity.spring18.dbschema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Base Controller
 */
@Controller
@RequestMapping(path = "")
public class BaseController {
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    /**
     * Main controller that we will use the modelandview in
     *
     * @param value The user name
     * @return A view of the links for a given user
     */
    @GetMapping("/sampleUI")
    public ModelAndView sample_view(@RequestParam(value = "name", defaultValue = "User") String value) {
        ModelAndView result = new ModelAndView();
        result.addObject("userName", value);
        result.setViewName("sampleAnalyticsUI/sample");

        List<Link> links = linkRepository.findByPublisherID(1L);
        result.addObject("links", links);

        // Get all the Links statistics based on all clicks for each link
        List<Link_Stat> link_stats = new ArrayList();
        for (Link l : links) {
            List<Click> clicks = clickRepository.findByLinkID(l.getLinkID());
            link_stats.add(new Link_Stat(clicks, l.getOriginalURL()));
        }
        result.addObject("link_stats", link_stats);

        return result;
    }

    public class Link_Stat {
        private Long TotalUnitsOrdered;
        private int TotalConvertedToSale;
        private String OriginalURL;

//        private Map<Long, Integer> StatMap;

        //combine the # convertedToSale and unitsOrdered based on the clicks of that link
        public Link_Stat(List<Click> clicks, String orig_URL) {
            TotalUnitsOrdered = 0L;
            TotalConvertedToSale = 0;
            for (Click c : clicks) {
                if (c.getUnitsOrdered() > 0)
                    TotalUnitsOrdered += c.getUnitsOrdered();
                TotalConvertedToSale += (c.getConvertedToSale()) ? 1 : 0;
            }
            OriginalURL = orig_URL;
        }

        public Long getTotalUnitsOrdered() {
            return TotalUnitsOrdered;
        }

        public int getTotalConvertedToSale() {
            return TotalConvertedToSale;
        }

        public String getOriginalURL() {
            return OriginalURL;
        }
    }


    /**
     * Get the Links with the associated publisherID or merchantID if provided.
     *
     * @param publisherID The ID of an associated publisher
     * @param merchantID  The ID of an associated merchant
     * @return A list of links, filtered by the given IDs if provided
     */
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
     * @param id The ID of a link
     * @return The link with the given ID or null if it does not exist
     */
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Link getLinkByID(@PathVariable(value = "id") Long id) {
        Optional<Link> res = linkRepository.findById(id);
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
     * @param groupName                  The name of the group that the link belongs to
     * @return The newly created Link
     */
    @PostMapping(path = "")
    public @ResponseBody
    Link saveLink(@RequestParam(value = "publisherID") Long publisherID,
                  @RequestParam(value = "merchantID") Long merchantID,
                  @RequestParam(value = "earnings") Double earnings,
                  @RequestParam(value = "customTitle", defaultValue = "New Link") String customTitle,
                  @RequestParam(value = "originalURL") String originalURL,
                  @RequestParam(value = "imageRedirectPermahashLink") String imageRedirectPermahashLink,
                  @RequestParam(value = "groupName") String groupName) {
        if (!publisherRepository.existsById(publisherID) || !merchantRepository.existsById(merchantID)) {
            return null;
        }
        Link created = new Link(publisherID, merchantID, earnings, customTitle, originalURL, imageRedirectPermahashLink, groupName);
        return linkRepository.save(created);
    }

    /**
     * Updates the Link with the given ID if it exists with the provided information.
     *
     * @param id          The ID of a Link
     * @param earnings    The new earnings of the link
     * @param customTitle The new title of the link
     * @return The updated Link, or null if the original ID did not exist
     */
    @PostMapping(path = "/{id}")
    public @ResponseBody
    Link saveLinkByID(@PathVariable(value = "id") Long id,
                      @RequestParam(value = "earnings", defaultValue = "-1.0") Double earnings,
                      @RequestParam(value = "customTitle", defaultValue = "") String customTitle) {
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
     *
     * @param id The ID of a Link
     */
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    void deleteLink(@PathVariable(value = "id") Long id) {
        if (linkRepository.existsById(id))
            linkRepository.deleteById(id);
        List<Click> clicks = clickRepository.findByLinkID(id);
        if (clicks != null)
            clickRepository.deleteAll(clicks);
    }
}
