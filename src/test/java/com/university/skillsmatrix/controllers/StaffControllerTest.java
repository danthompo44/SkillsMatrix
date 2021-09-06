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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffControllerTest {
    private final String REDIRECTED_LOGIN = "http://localhost/login";

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
    @WithMockUser(roles = "ADMIN")
    public void test01_When_Given_A_Request_For_All_Staff_With_A_Role_Of_Admin_Then_Return_All() throws Exception{
        mockMvc.perform(get("/staff/admin/all/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllStaff"))
                .andExpect(model().attributeExists("staffList"));
    }

    @Test
    public void test02_When_Given_A_Request_For_All_Staff_With_No_Role_Then_Redirect() throws Exception{
        mockMvc.perform(get("/staff/admin/all/"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test03_When_Given_A_Request_For_Staff_Members_Skills_With_A_Role_Of_Admin_Then_Return_Skills() throws Exception{
        mockMvc.perform(get("/staff/admin/skillPage/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewSkillsByStaff"))
                .andExpect(model().attributeExists("staffSkills"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    public void test04_When_Given_A_Request_For_Staff_Members_Skills_With_No_Role_Then_Redirect() throws Exception{
        mockMvc.perform(get("/staff/admin/skillPage/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test05_When_Given_A_Request_For_Managers_Staff_With_A_Role_Of_Admin_Then_Return_Managers_Staff() throws Exception{
        mockMvc.perform(get("/staff/admin/managerPage/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewManagersStaff"))
                .andExpect(model().attributeExists("staffList"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    public void test06_When_Given_A_Request_For_Managers_Staff_With_No_Role_Then_Redirect() throws Exception{
        mockMvc.perform(get("/staff/admin/managerPage/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }
}
