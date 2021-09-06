package com.university.skillsmatrix.controllers;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IndexControllerTest {
    private final String ERROR = "/error/";
    private final String ERRORPAGE = "errorPage";

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser("ADMIN")
    public void test01_When_An_Admin_Visits_A_Route_With_No_Mapping_Error_Is_Shown() throws Exception {
        mockMvc.perform(get(ERROR)).andExpect(view().name(ERRORPAGE));
    }

    @Test
    @WithMockUser("USER")
    public void test02_When_A_User_Visits_A_Route_With_No_Mapping_Error_Is_Shown() throws Exception {
        mockMvc.perform(get(ERROR)).andExpect(view().name(ERRORPAGE));
    }
}
