package com.sylconnexity.spring18.controller;

import com.sylconnexity.spring18.dbschema.Click;
import com.sylconnexity.spring18.dbschema.ClickRepository;
import com.sylconnexity.spring18.dbschema.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for the Click class, the API of which is available at /clicks.
 */
@Controller
@RequestMapping(path="/clicks")
public class DBController_Click {
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
    public @ResponseBody
    Iterable<Click> getClicks(@RequestParam(value="linkID", defaultValue="-1") Long linkID) {
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
