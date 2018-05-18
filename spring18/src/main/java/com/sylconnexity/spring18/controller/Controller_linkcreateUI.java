package com.sylconnexity.spring18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Controller_linkcreateUI {
    @GetMapping("/linkcreate")
    public ModelAndView sample_view(@RequestParam(value = "name", defaultValue="User") String value){
        ModelAndView result = new ModelAndView();
        result.addObject("userName", value);

        result.setViewName("linkcreate");
        return result;
    }
}
