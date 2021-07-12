package com.safecornerscoffee.medium.service;

import com.safecornerscoffee.medium.mapper.TimeMapper;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private final TimeMapper timeMapper;

    public TimeService(TimeMapper timeMapper) {
        this.timeMapper = timeMapper;
    }

    public String getCurrentTime() {
        return timeMapper.getCurrentTime();
    }
}
