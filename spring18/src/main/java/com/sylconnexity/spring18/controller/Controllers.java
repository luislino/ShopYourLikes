package com.sylconnexity.spring18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
class index_Controller {

    @GetMapping("/")
    public ModelAndView example(@RequestParam(value = "name", defaultValue="User") String value){
        ModelAndView result = new ModelAndView();
        result.addObject("userName", value);

        result.setViewName("index");

        return result;
    }

}

// Controller for a sample of an analytics UI
@Controller
class sampleUI_Controller {
    @GetMapping("/sampleUI")
    public ModelAndView sample_view(){
        ModelAndView result = new ModelAndView();
        result.setViewName("sampleAnalyticsUI/sample");
        return result;
    }


}
