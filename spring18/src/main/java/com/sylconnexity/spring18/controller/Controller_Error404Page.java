package com.sylconnexity.spring18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the 404 page.
 */
@Controller
@RequestMapping("/error")
public class Controller_Error404Page {

    /**
     * Displays a custom 404 page.
     *
     * @return A 404 error page
     */
    public ModelAndView error404Page() {
        ModelAndView result = new ModelAndView();
        result.setViewName("error");
        return result;
    }
}
