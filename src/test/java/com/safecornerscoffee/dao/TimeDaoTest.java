package com.safecornerscoffee.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
public class TimeDaoTest extends TestCase {

    @Autowired
    private TimeDao timeDao;

    @Test
    public void testGetCurrentTime() {
        System.out.println(timeDao.getCurrentTime());
    }
}