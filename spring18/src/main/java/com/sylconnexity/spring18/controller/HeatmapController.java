package com.sylconnexity.spring18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HeatmapController {

    @GetMapping("/heatmap")
    public ModelAndView Heatmap_page(@RequestParam(value = "linkID", defaultValue = "") String value) {
        ModelAndView result = new ModelAndView();
        if (value.equals("")) {
            result.addObject("control", true);
            result.addObject("linkid", "");
        } else {
            result.addObject("linkid", value);
            result.addObject("control", false);
        }

        return result;
    }

}
