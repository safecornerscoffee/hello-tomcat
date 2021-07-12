package com.safecornerscoffee.medium.web;


import com.safecornerscoffee.medium.service.LoggerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoggerController {

    private LoggerService loggerService;

    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping("/log")
    @ResponseBody
    public String getLogger() {
        loggerService.log();
        return "logged";
    }
}
