package com.sylconnexity.spring18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping(path="/")
    public @ResponseBody Iterable<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Merchant getMerchantByID(@PathVariable(value="id") Long id) {
        Optional<Merchant> res = merchantRepository.findById(id);
        if (res.isPresent())
            return res.get();
        else
            return null;
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