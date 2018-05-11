package com.sylconnexity.spring18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * test.
 */
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
    public ModelAndView sample_view(@RequestParam(value = "name", defaultValue="User") String value){
        ModelAndView result = new ModelAndView();
        result.addObject("userName", value);

        result.setViewName("sampleAnalyticsUI/sample");
        return result;
    }


}

@Controller
@RequestMapping("/error")
class error_404_Controller {
    public ModelAndView error404Page(){
        ModelAndView result = new ModelAndView();
        result.setViewName("error");
        return result;
    }
}
