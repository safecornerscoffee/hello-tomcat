package com.safecornerscoffee.integration.controller;

import com.safecornerscoffee.controller.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/web/WEB-INF/applicationContext.xml", "file:src/main/web/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class IndexControllerTest {

    @Autowired
    IndexController indexController;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void beforeEach() {
//        mockMvc = standaloneSetup(indexController).build();
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void renderIndexPageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/views/index.jsp"));
    }

}