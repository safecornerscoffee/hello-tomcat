package com.safecornerscoffee.controller;

import com.safecornerscoffee.service.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TimeController {

    TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/time")
    @ResponseBody
    public String currentTime() {
        return timeService.getCurrentTime();
    }
}
