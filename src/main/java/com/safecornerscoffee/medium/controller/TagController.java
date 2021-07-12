package com.safecornerscoffee.medium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TagController {

    @GetMapping("/tags/")
    @ResponseBody
    public String returnTags() {
        return "redirect:/";
    }
}
