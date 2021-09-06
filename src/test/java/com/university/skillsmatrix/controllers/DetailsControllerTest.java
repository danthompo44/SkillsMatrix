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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DetailsControllerTest {
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
    @WithMockUser(roles = "USER")
    public void test01_When_Given_A_Request_For_All_Details_With_A_Role_Of_User_Then_Return_All() throws Exception{
        mockMvc.perform(get("/details/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewMyDetails"))
                .andExpect(model().attributeExists("details"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test02_When_Given_A_Request_For_All_Details_With_A_Role_Of_Admin_Return_Error() throws Exception{
        mockMvc.perform(get("/details/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test03_When_Given_A_Request_For_All_Details_With_No_Role_Then_Redirect() throws Exception{
        mockMvc.perform(get("/details/"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test04_When_Given_A_Valid_Request_To_Update_Details_As_A_User_Return_All_Details() throws Exception{
        mockMvc.perform(post("/details/update/1")
                .with(csrf())
                .param("firstName", "First Name")
                .param("surname", "Surname")
                .param("addressFirstLine", "Address First Line")
                .param("addressSecondLine", "Address Second Line")
                .param("county", "County")
                .param("postcode", "Postcode"))
                .andDo(print())
                .andExpect(view().name("viewMyDetails"))
                .andExpect(model().attributeExists("details"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test05_When_Given_A_Valid_Request_To_Update_Details_As_An_Admin_Return_Error() throws Exception{
        mockMvc.perform(post("/details/update/1")
                .with(csrf())
                .param("firstName", "First Name")
                .param("surname", "Surname")
                .param("addressFirstLine", "Address First Line")
                .param("addressSecondLine", "Address Second Line")
                .param("county", "County")
                .param("postcode", "Postcode"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test06_When_Given_A_Valid_Request_To_Update_Details_With_No_Role_Return_Login() throws Exception{
        mockMvc.perform(post("/details/update/1")
                .with(csrf())
                .param("firstName", "First Name")
                .param("surname", "Surname")
                .param("addressFirstLine", "Address First Line")
                .param("addressSecondLine", "Address Second Line")
                .param("county", "County")
                .param("postcode", "Postcode"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test07_When_Given_An_Invalid_Request_To_Update_Details_As_A_User_Return_All_Details_With_Error() throws Exception{
        mockMvc.perform(post("/details/update/1")
                .with(csrf())
                .param("firstName", "")
                .param("surname", "Surname")
                .param("addressFirstLine", "Address First Line")
                .param("addressSecondLine", "Address Second Line")
                .param("county", "County")
                .param("postcode", "Postcode"))
                .andDo(print())
                .andExpect(view().name("viewMyDetails"))
                .andExpect(model().attributeExists("details"))
                .andExpect(model().attributeExists("errorString"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test08_When_Given_A_Valid_Request_To_Delete_Details_As_A_User_Return_View_All_Details() throws Exception {
        mockMvc.perform(post("/details/delete/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(view().name("viewMyDetails"))
                .andExpect(model().attributeExists("details"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test09_When_Given_A_Valid_Request_To_Delete_Details_As_A_Admin_Return_Client_Error() throws Exception {
        mockMvc.perform(post("/details/delete/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test10_When_Given_A_Valid_Request_To_Delete_Details_With_No_User_Return_Login() throws Exception {
        mockMvc.perform(post("/details/delete/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }
}
