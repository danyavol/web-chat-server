package com.danvol.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
//    @GetMapping("/")
//    public ModelAndView openHomePage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("test.html");
//        return modelAndView;
//    }
    @GetMapping("/")
    public String openHomePage() {
        return "../../webapp/dist/index";
    }
}
