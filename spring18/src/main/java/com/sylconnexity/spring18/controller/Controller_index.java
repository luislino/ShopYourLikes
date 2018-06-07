package com.sylconnexity.spring18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the default index.
 */
@Controller
public class Controller_index {

    /**
     * Displays an index page for a given user.
     *
     * @param value The name of a user.
     * @return An index page customized for the given user.
     */
    @GetMapping("/")
    public ModelAndView example(@RequestParam(value = "name", defaultValue = "User") String value) {
        ModelAndView result = new ModelAndView();
        result.addObject("userName", value);
        result.setViewName("index");

        return result;
    }

}
