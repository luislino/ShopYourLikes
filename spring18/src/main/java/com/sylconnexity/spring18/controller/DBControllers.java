package com.sylconnexity.spring18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sylconnexity.spring18.dbschema.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/clicks")
class ClickController {
    @Autowired
    private ClickRepository clickRepository;

    @GetMapping(path="/")
    public @ResponseBody Iterable<Click> getAllClicks() {
        return clickRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Click getClickByID(@PathVariable(value="id") Long id) {
        Optional<Click> res = clickRepository.findById(id);
        if (res.isPresent())
            return res.get();
        else
            return null;
    }
}

@Controller
@RequestMapping(path="/links")
class LinkController {
    @Autowired
    private LinkRepository linkRepository;

    @GetMapping(path="/")
    public @ResponseBody Iterable<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Link getLinkByID(@PathVariable(value="id") Long id) {
        Optional<Link> res = linkRepository.findById(id);
        if (res.isPresent())
            return res.get();
        else
            return null;
    }
}

@Controller
@RequestMapping(path="/merchants")
class MerchantController {
    @Autowired
    private MerchantRepository merchantRepository;

    @GetMapping(path="")
    public @ResponseBody Iterable<Merchant> getMerchants(@RequestParam(value="merchantName", defaultValue="") String merchantName) {
        if (merchantName.equals(""))
            return merchantRepository.findAll();
        else
            return merchantRepository.findByMerchantName(merchantName);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Merchant getMerchantByID(@PathVariable(value="id") Long id) {
        Optional<Merchant> res = merchantRepository.findById(id);
        if (res.isPresent())
            return res.get();
        else
            return null;
    }

    @PostMapping(path="")
    public @ResponseBody Merchant saveMerchant(@RequestParam(value="merchantName") String merchantName) {
        Merchant created = new Merchant(merchantName);
        return merchantRepository.save(created);
    }

    @PostMapping(path="/{id}")
    public @ResponseBody Merchant saveMerchantByID(@PathVariable(value="id") Long id,
                                                   @RequestParam(value="merchantName") String merchantName) {
        if (merchantRepository.existsById(id)) {
            Merchant old = merchantRepository.findById(id).get();
            old.setMerchantName(merchantName);
            return merchantRepository.save(old);
        }
        return null;
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody void deleteMerchant(@PathVariable(value="id") Long id) {
        if (merchantRepository.existsById(id))
            merchantRepository.deleteById(id);
    }
}

@Controller
@RequestMapping(path="/publishers")
class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping(path="/")
    public @ResponseBody Iterable<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Publisher getPublisherByID(@PathVariable(value="id") Long id) {
        Optional<Publisher> res = publisherRepository.findById(id);
        if (res.isPresent())
            return res.get();
        else
            return null;
    }
}