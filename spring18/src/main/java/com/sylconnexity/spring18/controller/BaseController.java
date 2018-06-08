package com.sylconnexity.spring18.controller;

import com.sylconnexity.spring18.dbschema.*;
import com.sylconnexity.spring18.util.RetailLinkList;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

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

        //Make groups
        List<Link> links = linkRepository.findByPublisherID(1L);
        Map<String, Group> groups = new HashMap();
        List<Link_Stat> link_stats = new ArrayList();
        //Create 10 groups
       // int i = 0;
        for(Link l : links){
            if(true) {
                Group l_group = groups.get(l.getGroupName());
                if (l_group == null) {
                    l_group = new Group(l.getGroupName());
                    groups.put(l.getGroupName(), l_group);
                }
                l_group.addLink(l);
            }
            //String group_name = "Group " + Integer.toString(i % 10);
            //l.setGroupName(group_name);
            //Making the groups
        /*boolean containsGroup = false;
        for(Group g : groups){
            if(g.getGroupName().equals(group_name)){
                g.addLink(l);
                containsGroup = true;
                break;
            }
        }
        */
            Long id = l.getLinkID();
            List<Click> clicks = clickRepository.findByLinkID(id);
            link_stats.add(new Link_Stat(clicks, l.getOriginalURL(), l.getGroupName(), id));
        /*
        //add group if group doesn't contain
        if(containsGroup == false ){
            Group gr = new Group();
            gr.addLink(l);
            gr.setGroupName(group_name);
            groups.add(gr);
        }
        i++;
        */

        }
        result.addObject("groups", groups.values());

//        for(Group g : groups){
//            List<Click> clicks= clickRepository.findByLinkID(l.getLinkID());
//            link_stats.add(new Link_Stat(clicks, l.getOriginalURL()));
//        }
        // Get all the Links statistics based on all clicks for each link
        /* Done above
        List<Link_Stat> link_stats = new ArrayList();
        for (Link l: links){
            Long id = l.getLinkID();
            List<Click> clicks= clickRepository.findByLinkID(id);
            link_stats.add(new Link_Stat(clicks, l.getOriginalURL(), l.getGroupName(), id));

        }
        */
        result.addObject("link_stats", link_stats);

        return result;
    }

    private class Group{
        //private List<Link> Links;
        private String GroupName;
        private Double Earnings;
        private Long MerchantID;
        private int NumberOfLinks;
        public Group(String groupName){
            //Links = new ArrayList();
            GroupName = groupName;
            Earnings = 0.0;
            NumberOfLinks = 0;
        }

        //When you add a link, you add to the Link_stats list as well
        public void addLink(Link link){
            //Links.add(link);
            Earnings += link.getEarnings();
            NumberOfLinks++;
            //Merchant ID doesn't make sense in this context as each link in a group may
            //have a different merchant id
            MerchantID = link.getMerchantID();
            //Link_stats

        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String groupName) {
            GroupName = groupName;
        }

        public Double getEarnings() {
            return Earnings;
        }

        public Long getMerchantID() {
            return MerchantID;
        }
    }

    // TODO: request publisherID and API key through POST data
    private String PUBLISHER_ID = "628668";
    private String API_KEY = "45c95d7b2796ffd28de48f4307bceb45";
    private String convertToSylLink(String retailLink, String publisherID, String apiKey){
        String BASE_API_URL = "http://api.shopyourlikes.com/api/link/generate";

        return BASE_API_URL + "?url=" + retailLink + "&publisherId=" + publisherID + "&apiKey=" + apiKey;
    }

    @GetMapping("/batchForm")
    public ModelAndView batchForm(@RequestParam(value = "name", defaultValue = "User") String value) {
        ModelAndView result = new ModelAndView();
        result.addObject("userName", value);
        result.setViewName("batch");
        return result;
    }
/*
    @GetMapping("/batchForm")
    public ModelAndView batchForm() {
        return new ModelAndView("batch", "example", new RetailLinkList());
    }
    */
    /*
       @PostMapping("/batchPost")
       public ModelAndView submitForm(@ModelAttribute RetailLinkList example) {

           String originalLinkList = example.getListOfLinks();
           String convertedLinkList = "";
           String removeCarriage = example.getListOfLinks();
           removeCarriage = removeCarriage.replaceAll("\r\n", "\n");
           String[] retailLinkArray = removeCarriage.split("\\n");


           for (int i = 0; i < retailLinkArray.length; i++){
               String completeURL = convertToSylLink(retailLinkArray[i], PUBLISHER_ID, API_KEY) + "\n";
               try {
                   URL sylURL = new URL(completeURL);
                   URLConnection sylConnection = sylURL.openConnection();
                   BufferedReader connectionBuff = new BufferedReader(new InputStreamReader(sylConnection.getInputStream()));

                   String inputLine;
                   StringBuffer jsonBuffer= new StringBuffer();
                   while ((inputLine = connectionBuff.readLine()) != null){
                       jsonBuffer.append(inputLine);
                   }
                   connectionBuff.close();
                   JSONObject sylJsonResponse = new JSONObject(jsonBuffer.toString());
                   convertedLinkList = convertedLinkList.concat(sylJsonResponse.getString("link")).concat("\n");
               }
               catch (MalformedURLException e) {}
               catch (IOException e){}
           }
           example.setListOfLinks(convertedLinkList);
           ModelAndView result = new ModelAndView("outputBatch","exampleOut", example);
           result.addObject("originalRetailList", originalLinkList);
           return result;
       }
       */
    public class Link_Stat {
        private Long TotalUnitsOrdered;
        private int TotalConvertedToSale;
        private String OriginalURL;
        private String groupName;
        private Long LinkID;
        //combine the # convertedToSale and unitsOrdered based on the clicks of that link
        public Link_Stat(List<Click> clicks, String orig_URL, String groupName, Long link_id){
            TotalUnitsOrdered = 0L;
            TotalConvertedToSale = 0;
            for (Click c : clicks) {
                if (c.getUnitsOrdered() > 0)
                    TotalUnitsOrdered += c.getUnitsOrdered();
                TotalConvertedToSale += (c.getConvertedToSale()) ? 1 : 0;
            }
            OriginalURL = orig_URL;
            LinkID = link_id;
            this.groupName = groupName;
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

        public String getGroupName() {
            return groupName;
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
