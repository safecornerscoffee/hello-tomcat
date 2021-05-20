package com.safecornerscoffee.service;

import com.safecornerscoffee.dao.TimeDao;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private final TimeDao timeDao;

    public TimeService(TimeDao timeDao) {
        this.timeDao = timeDao;
    }

    public String getCurrentTime() {
        return timeDao.getCurrentTime();
    }
}
